package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.remoting.Server;
import com.git.wuqf.xiaokuo.common.URL;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;

/**
 * Created by wuqf on 17-2-25.
 */
public abstract class AbstractServer extends AbstractEndpoint implements Server {
    private InetSocketAddress localAddress;

    private InetSocketAddress bindAddress;

    private int accepts;

    public AbstractServer(URL url, ChannelHandler channelHandler) {
        super(url, channelHandler);
        localAddress = new InetSocketAddress(url.getHost(), url.getPort());
        bindAddress = new InetSocketAddress(url.getHost(), url.getPort());
        ;
        doOpen();
    }

    protected abstract void doOpen();

    protected abstract void doClose();

    @Override
    public boolean isBound() {
        return false;
    }


    @Override
    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        Collection<Channel> channels = getChannels();
        for (Channel channel : channels) {
            channel.send(message, sent);
        }
    }

    @Override
    public void send(Object message) throws RemotingException {
        Collection<Channel> channels = getChannels();
        for (Channel channel : channels) {
            channel.send(message);
        }
    }

    public SocketAddress getBindAddress() {
        return bindAddress;
    }

    public void close() {
        doClose();
    }

}
