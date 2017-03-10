package com.git.wuqf.xiaokuo.common.serialize.support.java;

import com.git.wuqf.xiaokuo.common.serialize.ObjectInput;
import com.git.wuqf.xiaokuo.common.serialize.ObjectOutput;
import com.git.wuqf.xiaokuo.common.serialize.Serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by wuqf on 17-2-26.
 */
public class JavaSerialization implements Serialization {
    @Override
    public byte getContentTypeId() {
        return 0;
    }

    @Override
    public String getContentType() {
        return "x-application/java";
    }

    @Override
    public ObjectOutput serialize(URL url, OutputStream output) throws IOException {
        return null;
    }

    @Override
    public ObjectInput deserialize(URL url, InputStream input) throws IOException {
        return null;
    }
}
