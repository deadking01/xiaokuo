package com.git.wuqf.remoting;

import com.git.wuqf.xiaokuo.common.Constants;
import com.git.wuqf.xiaokuo.common.extension.Adaptive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wuqf on 17-2-26.
 */
public interface Codec {

    /**
     * Need more input poison.
     *
     * @see #decode(Channel, InputStream)
     */
    Object NEED_MORE_INPUT = new Object();

    /**
     * Encode message.
     *
     * @param channel channel.
     * @param output  output stream.
     * @param message message.
     */
    void encode(Channel channel, OutputStream output, Object message) throws IOException;

    /**
     * Decode message.
     *
     * @param channel channel.
     * @param input   input stream.
     * @return message or <code>NEED_MORE_INPUT</code> poison.
     * @see #NEED_MORE_INPUT
     */
    Object decode(Channel channel, InputStream input) throws IOException;

}
