package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.Server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Collection;

/**
 * Created by wuqf on 17-2-25.
 */
public abstract class AbstractServer extends AbstractEndpoint implements Server {
    private SocketAddress localAddress;

    private SocketAddress bindAddress;

    private int accepts;

    public AbstractServer(URL url, ChannelHandler channelHandler) {
        super(url, channelHandler);
        localAddress = new InetSocketAddress(url.getHost(),url.getPort());
        bindAddress =  new InetSocketAddress(url.getHost(),url.getPort());;
    }

    @Override
    public boolean isBound() {
        return false;
    }

    @Override
    public Collection<Channel> getChannels() {

        return null;
    }

    @Override
    public Channel getChannel(InetSocketAddress remoteAddress) {
        return null;
    }

    @Override
    public SocketAddress getLocalAddress() {
        return localAddress;
    }

    @Override
    public void send(Object message, boolean sent) {
        Collection<Channel> channels = getChannels();
        for(Channel channel :channels){
            channel.send(message,sent);
        }
    }

    @Override
    public void send(Object message) {
        Collection<Channel> channels = getChannels();
        for(Channel channel :channels){
            channel.send(message);
        }
    }
    public SocketAddress getBindAddress() {
        return bindAddress;
    }

}
