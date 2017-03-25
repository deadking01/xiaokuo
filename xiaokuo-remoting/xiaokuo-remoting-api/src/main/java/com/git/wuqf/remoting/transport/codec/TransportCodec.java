/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.git.wuqf.remoting.transport.codec;


import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.buffer.ChannelBuffer;
import com.git.wuqf.remoting.buffer.ChannelBufferInputStream;
import com.git.wuqf.remoting.buffer.ChannelBufferOutputStream;
import com.git.wuqf.remoting.transport.AbstractCodec;
import com.git.wuqf.xiaokuo.common.serialize.ObjectInput;
import com.git.wuqf.xiaokuo.common.serialize.ObjectOutput;
import com.git.wuqf.xiaokuo.common.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TransportCodec
 *
 * @author william.liangf
 */
public class TransportCodec extends AbstractCodec {

    public void encode(Channel channel, ChannelBuffer buffer, Object message) throws IOException {
        OutputStream output = new ChannelBufferOutputStream(buffer);
        ObjectOutput objectOutput = getSerialization(channel).serialize(channel.getUrl(), output);
        encodeData(channel, objectOutput, message);
        objectOutput.flushBuffer();
    }

    public Object decode(Channel channel, ChannelBuffer buffer) throws IOException {
        InputStream input = new ChannelBufferInputStream(buffer);
        return decodeData(channel, getSerialization(channel).deserialize(channel.getUrl(), input));
    }

    protected void encodeData(Channel channel, ObjectOutput output, Object message) throws IOException {
        encodeData(output, message);
    }

    protected Object decodeData(Channel channel, ObjectInput input) throws IOException {
        return decodeData(input);
    }

    protected void encodeData(ObjectOutput output, Object message) throws IOException {
        output.writeObject(message);
    }

    protected Object decodeData(ObjectInput input) throws IOException {
        try {
            return input.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("ClassNotFoundException: " + StringUtils.toString(e));
        }
    }
}