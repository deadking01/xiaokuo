package com.git.wuqf.xiaokuo.remoting.netty;

import com.git.wuqf.xiaokuo.remoting.transport.AbstractClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import com.git.wuqf.xiaokuo.common.URL;

import java.net.InetSocketAddress;

/**
 * Created by wuqf on 17-2-24.
 */
public class NettyClient extends AbstractClient {

    private Bootstrap bootstrap;
    private Channel channel;
    private com.git.wuqf.xiaokuo.remoting.ChannelHandler channelHandler;
    private EventLoopGroup group;

    public NettyClient(URL url, com.git.wuqf.xiaokuo.remoting.ChannelHandler channelHandler) {
        super(url, channelHandler);
    }

    protected void doOpen() {
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        NettyHandler nettyHandler = new NettyHandler(getUrl(), this);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler(LogLevel.TRACE));
                        p.addLast(
                                new ObjectEncoder(),
                                new ObjectDecoder(ClassResolvers.cacheDisabled(null)), nettyHandler);
                    }
                });
    }

    @Override
    protected void doClose() {
        group.shutdownGracefully();
    }

    @Override
    protected void doConnect() {
        ChannelFuture f = null;
        try {
            f = bootstrap.connect(getUrl().getHost(), getUrl().getPort()).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel = f.channel();
    }

    @Override
    protected void doDisconnect() {
        NettyChannel.removeChannelIfDisconnected(channel);
    }

    @Override
    protected com.git.wuqf.xiaokuo.remoting.Channel getChannel() {
        return NettyChannel.getOrAddChannel(channel, getUrl(), this);
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean hasAttribute(String key) {
        return false;
    }

    @Override
    public Object getAttribute(String key) {
        return null;
    }

    @Override
    public void setAttribute(String key, Object value) {

    }

    @Override
    public void removeAttribute(String key) {

    }
}
