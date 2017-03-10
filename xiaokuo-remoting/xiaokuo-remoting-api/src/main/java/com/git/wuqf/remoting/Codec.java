package com.git.wuqf.remoting;

import com.git.wuqf.remoting.buffer.ChannelBuffer;

/**
 * Created by wuqf on 17-2-26.
 */
public interface Codec {

    void encode(Channel channel, ChannelBuffer channelBuffer, Object message);

    Object decode(Channel cHannel, ChannelBuffer channelBuffer);
}
