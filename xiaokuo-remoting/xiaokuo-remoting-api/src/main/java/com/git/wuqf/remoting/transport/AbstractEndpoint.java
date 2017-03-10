package com.git.wuqf.remoting.transport;

import com.git.wuqf.remoting.ChannelHandler;

import java.net.URL;

/**
 * Created by wuqf on 17-2-25.
 */
public abstract class AbstractEndpoint extends AbstractPeer {

    public AbstractEndpoint(URL url, ChannelHandler channelHandler) {
        super(url, channelHandler);
    }
    public void reset(URL url) {

    }
}