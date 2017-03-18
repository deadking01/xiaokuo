package com.git.wuqf.remoting.support.header;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.exchange.ExchangeChannel;
import com.git.wuqf.remoting.exchange.ExchangeHandler;
import com.git.wuqf.remoting.exchange.ResponseFuture;
import com.git.wuqf.xiaokuo.common.Constants;
import com.git.wuqf.xiaokuo.common.URL;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
//import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-3-18.
 */
public class HeaderExchangeChannel implements ExchangeChannel {

    private static String CHANNEL_KEY=HeaderExchangeChannel.class.getName()+"CHANNEL";

    private Channel channel;

    private boolean closed=false;

    HeaderExchangeChannel(Channel channel){
        if (channel == null) {
            throw new IllegalArgumentException("channel == null");
        }
        this.channel = channel;
    }

    @Override
    public ResponseFuture request(Object request) {
        return request(request, channel.getUrl().getPositiveParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT));
    }

    @Override
    public ResponseFuture request(Object request, int timeout) {
        return null;
    }

    @Override
    public ExchangeHandler getExchangeHandler() {
        return null;
    }

    @Override
    public void close(int timeout) {

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

    @Override
    public URL getUrl() {
        return null;
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return null;
    }

    @Override
    public SocketAddress getLocalAddress() {
        return null;
    }

    @Override
    public void send(Object message, boolean sent) {

    }

    @Override
    public void send(Object message) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
