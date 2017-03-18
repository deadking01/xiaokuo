package com.git.wuqf.remoting;

import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-2-24.
 */
public interface Transporter {

    Server bind(URL url, ChannelHandler channelHandler);

    Client connect(URL url, ChannelHandler channelHandler);
}
