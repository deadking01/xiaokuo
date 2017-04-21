package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.Client;
import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.xiaokuo.common.URL;

import java.net.InetSocketAddress;

/**
 * Created by wuqf on 17-2-25.
 */
public abstract class AbstractClient extends AbstractEndpoint implements Client {

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
    public InetSocketAddress getLocalAddress() {
        Channel channel=getChannel();
        return channel.getRemoteAddress();
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        Channel channel = getChannel();
        channel.send(message,sent);
    }

    @Override
    public void send(Object message) throws RemotingException {
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
    public void reconnect() {
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
    protected abstract void doDisconnect() ;

    protected abstract Channel getChannel();
}
