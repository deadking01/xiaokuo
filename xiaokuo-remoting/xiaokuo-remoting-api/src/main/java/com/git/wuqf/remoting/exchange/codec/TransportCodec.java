package com.git.wuqf.remoting.exchange.codec;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.Codec;
import com.git.wuqf.remoting.buffer.ChannelBuffer;
import com.git.wuqf.remoting.buffer.ChannelBufferInputStream;
import com.git.wuqf.remoting.buffer.ChannelBufferOutputStream;
import com.git.wuqf.xiaokuo.common.serialize.ObjectInput;
import com.git.wuqf.xiaokuo.common.serialize.ObjectOutput;
import com.git.wuqf.xiaokuo.common.serialize.support.nativejava.NativeJavaObjectInput;
import com.git.wuqf.xiaokuo.common.serialize.support.nativejava.NativeJavaObjectOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by wuqf on 17-2-26.
 */
public class TransportCodec implements Codec {

    @Override
    public void encode(Channel channel, ChannelBuffer channelBuffer, Object message) {
        OutputStream output = new ChannelBufferOutputStream(channelBuffer);
        try {
            ObjectOutput objectOutput = new NativeJavaObjectOutput(output);
            encodeData(objectOutput, message);
            objectOutput.flushBuffer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object decode(Channel channel, ChannelBuffer channelBuffer) {
        InputStream input = new ChannelBufferInputStream(channelBuffer);
        try {
            ObjectInput objectInput = new NativeJavaObjectInput(input);
            return decodeData(objectInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void encodeData(ObjectOutput output, Object message) throws IOException {
        output.writeObject(message);
    }

    protected Object decodeData(ObjectInput input) {
        try {
            return input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
