package com.git.wuqf.remoting.netty;

import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.Client;
import com.git.wuqf.remoting.Server;
import com.git.wuqf.remoting.Transporter;

import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-2-25.
 */
public class NettyTransports implements Transporter {
    @Override
    public Server bind(URL url, ChannelHandler channelHandler) {
        return new NettyServer(url,channelHandler);
    }

    @Override
    public Client connect(URL url, ChannelHandler channelHandler) {
        return new NettyClient(url,channelHandler);
    }
}
