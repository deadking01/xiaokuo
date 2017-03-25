package com.git.wuqf.remoting;

import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.extension.SPI;

/**
 * Created by wuqf on 17-2-24.
 */
@SPI("netty")
public interface Transporter {

    Server bind(URL url, ChannelHandler channelHandler) throws RemotingException;

    Client connect(URL url, ChannelHandler channelHandler) throws RemotingException;
}
