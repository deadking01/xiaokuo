package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.EndPoint;

import java.net.URL;

/**
 * Created by wuqf on 17-2-25.
 */
public abstract class AbstractPeer implements EndPoint,ChannelHandler {

    private URL url;
    private ChannelHandler channelHandler;
    private boolean closed;

    public AbstractPeer(URL url, ChannelHandler channelHandler) {
        this.url = url;
        this.channelHandler = channelHandler;
    }

   
    public void connected(Channel channel) {
        channelHandler.connected(channel);
    }

   
    public void disConnected(Channel channel) {
        channelHandler.disConnected(channel);
    }

   
    public void sent(Channel channel, Object message) {
        channelHandler.sent(channel, message);
    }

   
    public void received(Channel channel, Object message) {
        channelHandler.received(channel, message);
    }

   
    public void caught(Channel channel, Throwable exception) {
        channelHandler.caught(channel, exception);
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setChannelHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    public URL getUrl() {
        return url;
    }

    public ChannelHandler getChannelHandler() {
        return channelHandler;
    }

    public void disconnected(Channel ch) {
        channelHandler.disConnected(ch);
    }

    
   
    public void close() {
        closed=true;
    }

   
    public void close(int timeout) {
        close();
    }

   
    public boolean isClosed() {
        return closed;
    }

}
