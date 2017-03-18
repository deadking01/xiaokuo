package com.git.wuqf.remoting.support.header;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.ExchangeServer;
import com.git.wuqf.remoting.Server;
import com.git.wuqf.remoting.exchange.ExchangeChannel;
import com.git.wuqf.xiaokuo.common.utils.NamedThreadFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import com.git.wuqf.xiaokuo.common.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by wuqf on 17-3-18.
 */
public class HeaderExchangeServer implements ExchangeServer{

    private ScheduledExecutorService service= Executors.newScheduledThreadPool(1,new NamedThreadFactory("xiaokuo-remote-server-heartbeat",true));

    private ScheduledFuture<?> heartbeatTimer;

    // 心跳超时，毫秒。缺省0，不会执行心跳。
    private int heatbeat;

    private int heartbeatTimeout;

    private final Server server;

    private volatile boolean closed=false;

    public HeaderExchangeServer(Server server){
        this.server=server;

    }
    @Override
    public Collection<ExchangeChannel> getExchangeChannels() {
        Collection<ExchangeChannel> exchangeChannels=new ArrayList<>();
        Collection<Channel> channels=server.getChannels();
        if(channels!=null&&channels.size()!=0){
            for(Channel channel:channels){
                exchangeChannels.add(HeaderExchangeChannel.getOrAddChannel(channel));
            }
        }
        return null;
    }

    @Override
    public ExchangeChannel getExchangeChannel(InetSocketAddress remoteAddress) {
        return null;
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
    public com.git.wuqf.xiaokuo.common.URL getUrl() {
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
    public void close(int timeout) {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void reset(URL url) {

    }
}
