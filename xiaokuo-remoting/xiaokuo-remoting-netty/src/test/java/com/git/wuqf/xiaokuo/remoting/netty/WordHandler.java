package com.git.wuqf.xiaokuo.remoting.netty;

import com.git.wuqf.xiaokuo.remoting.Channel;
import com.git.wuqf.xiaokuo.remoting.ChannelHandler;

/**
 * Created by wuqf on 17-3-11.
 */
public class WordHandler implements ChannelHandler {

    @Override
    public void connected(Channel channel) {

    }

    @Override
    public void disconnected(Channel channel) {

    }

    @Override
    public void sent(Channel channel, Object message) {

    }

    @Override
    public void received(Channel channel, Object message) {
        System.out.print(message);
    }

    @Override
    public void caught(Channel channel, Throwable exception) {

    }
}
