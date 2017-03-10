package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;

/**
 * Created by wuqf on 17-2-26.
 */
public class DecodeHandler implements ChannelHandler{

    protected ChannelHandler handler;

    public DecodeHandler(ChannelHandler channelHandler){
        this.handler=channelHandler;
    }
    @Override
    public void connected(Channel channel) {
        handler.connected(channel);
    }

    @Override
    public void disConnected(Channel channel) {
        handler.disConnected(channel);
    }

    @Override
    public void sent(Channel channel, Object message) {
        handler.sent(channel,message);
    }

    @Override
    public void received(Channel channel, Object message) {
        handler.received(channel,message);
    }

    @Override
    public void caught(Channel channel, Throwable exception) {

    }
}
