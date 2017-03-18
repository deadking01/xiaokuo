package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.Client;

import java.net.SocketAddress;
import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-2-25.
 */
public abstract class AbstractClient extends AbstractEndpoint implements Client, ChannelHandler {

    public AbstractClient(URL url, ChannelHandler channelHandler) {
        super(url,channelHandler);
        doOpen();
        doConnect();
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return getChannelHandler();
    }

    @Override
    public SocketAddress getLocalAddress() {
        Channel channel=getChannel();
        return channel.getRemoteAddress();
    }

    @Override
    public void send(Object message, boolean sent) {
        Channel channel = getChannel();
        channel.send(message,sent);
    }

    @Override
    public void send(Object message) {
        Channel channel = getChannel();
        channel.send(message);
    }
    protected void connect(){
        doConnect();
    }
    public void disconnect() {
        Channel channel = getChannel();
        if (channel != null) {
            channel.close();
        }
    }
    @Override
    public void reconnected() {
        disconnect();
        connect();
    }
    public void close() {
        disconnect();
    }
    /**
     * Open client.
     *
     * @
     */
    protected abstract void doOpen() ;

    /**
     * Close client.
     *
     * @
     */
    protected abstract void doClose() ;

    /**
     * Connect to server.
     *
     * @
     */
    protected abstract void doConnect() ;

    /**
     * disConnect to server.
     *
     * @
     */
    protected abstract void doDisConnect() ;

    protected abstract Channel getChannel();
}
