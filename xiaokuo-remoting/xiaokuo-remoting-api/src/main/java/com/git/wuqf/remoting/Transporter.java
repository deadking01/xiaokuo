package com.git.wuqf.remoting;

import java.net.URL;

/**
 * Created by wuqf on 17-2-24.
 */
public interface Transporter {

    Server bind(URL url, ChannelHandler channelHandler);

    Client Connect(URL url, ChannelHandler channelHandler);
}
