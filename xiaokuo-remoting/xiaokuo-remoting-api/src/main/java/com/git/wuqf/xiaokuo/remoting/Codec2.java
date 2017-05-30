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
package com.git.wuqf.xiaokuo.remoting;


import com.git.wuqf.xiaokuo.remoting.buffer.ChannelBuffer;

import java.io.IOException;

/**
 * @author <a href="mailto:gang.lvg@taobao.com">kimi</a>
 */
public interface Codec2 {

    void encode(Channel channel, ChannelBuffer buffer, Object message) throws IOException, RemotingException;

    Object decode(Channel channel, ChannelBuffer buffer) throws IOException;


    enum DecodeResult {
        NEED_MORE_INPUT, SKIP_SOME_INPUT
    }

}

