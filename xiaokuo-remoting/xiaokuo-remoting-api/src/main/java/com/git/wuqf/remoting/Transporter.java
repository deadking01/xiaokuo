package com.git.wuqf.remoting;

import java.util.List;

/**
 * Created by wuqf on 17-2-24.
 */
public interface Transporter {

    Server bind(String url, List<ChannelHandler> channelHandler);

    Client Connect(String url, List<ChannelHandler> channelHandler);
}
