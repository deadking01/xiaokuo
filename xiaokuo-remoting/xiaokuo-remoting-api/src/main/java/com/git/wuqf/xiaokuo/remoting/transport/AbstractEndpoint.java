package com.git.wuqf.xiaokuo.remoting.transport;

import com.git.wuqf.xiaokuo.remoting.ChannelHandler;

import com.git.wuqf.xiaokuo.common.URL;

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
