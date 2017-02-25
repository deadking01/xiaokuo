package com.git.wuqf.remoting.netty;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.transport.AbstractPeer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by wuqf on 17-2-25.
 */
public class NettyChannel extends AbstractPeer implements Channel {

    private final ChannelHandler handler;

    private volatile URL url;

    private volatile boolean closed;

    private static final ConcurrentMap<io.netty.channel.Channel, NettyChannel> channelMap = new ConcurrentHashMap<>();

    private io.netty.channel.Channel channel;

    private final Map<String, Object> attributes = new ConcurrentHashMap<>();

    public NettyChannel(io.netty.channel.Channel channel, URL url, ChannelHandler handler) {
        super(url, handler);
        this.channel=channel;
        this.handler = handler;
    }

    static NettyChannel getOrAddChannel(io.netty.channel.Channel ch, URL url, ChannelHandler handler) {

        NettyChannel ret = channelMap.get(ch);
        if (ret == null) {
            NettyChannel nc = new NettyChannel(ch, url, handler);
            if (ch.isActive()) {
                ret = channelMap.putIfAbsent(ch, nc);
            }
            if (ret == null) {
                ret = nc;
            }
        }
        return ret;
    }

    static void removeChannelIfDisconnected(io.netty.channel.Channel ch) {
        if (ch != null && !ch.isActive()) {
            channelMap.remove(ch);
        }
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) channel.remoteAddress();
    }

    @Override
    public boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    @Override
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public SocketAddress getLocalAddress() {
        return (InetSocketAddress) channel.localAddress();
    }

    @Override
    public void send(Object message, boolean sent) {
        channel.writeAndFlush(message);
    }

    @Override
    public void send(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public void close(int timeout) {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }
}
