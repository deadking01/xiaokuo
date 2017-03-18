package com.git.wuqf.remoting.support.header;

import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.Client;
import com.git.wuqf.remoting.ExchangeClient;
import com.git.wuqf.remoting.exchange.ExchangeChannel;
import com.git.wuqf.remoting.exchange.ExchangeHandler;
import com.git.wuqf.remoting.exchange.ResponseFuture;
import com.git.wuqf.xiaokuo.common.utils.NamedThreadFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import com.git.wuqf.xiaokuo.common.URL;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by wuqf on 17-3-18.
 */
public class HeaderExchangeClient implements ExchangeClient {

    private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, new NamedThreadFactory("xiaokuo-remoting-client-heartbeat", true));
    private ScheduledFuture<?> heartbeatTimer;

    //心跳超时时间，默认为0，不执行
    private int heartbeat;
    private int heartbeatTimeout;

    private Client client;

    private ExchangeChannel channel;

    @Override
    public InetSocketAddress getRemoteAddress() {
        return channel.getRemoteAddress();
    }

    @Override
    public boolean isConnected() {
        return channel.isConnected();
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

    @Override
    public void reconnected() {

    }

    @Override
    public ResponseFuture request(Object request) {
        return channel.request(request);
    }

    @Override
    public ResponseFuture request(Object request, int timeout) {
        return channel.request(request,timeout);
    }

    @Override
    public ExchangeHandler getExchangeHandler() {
        return channel.getExchangeHandler();
    }

    @Override
    public com.git.wuqf.xiaokuo.common.URL getUrl() {
        return channel.getUrl();
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return channel.getChannelHandler();
    }

    @Override
    public SocketAddress getLocalAddress() {
        return channel.getLocalAddress();
    }

    @Override
    public void send(Object message, boolean sent) {
        channel.send(message, sent);
    }

    @Override
    public void send(Object message) {
        channel.send(message);
    }

    @Override
    public void close() {
        channel.close();
    }

    @Override
    public void close(int timeout) {
        channel.close(timeout);
    }

    @Override
    public boolean isClosed() {
        return channel.isClosed();
    }

    @Override
    public void reset(URL url) {
        client.reset(url);
    }
}
