package com.git.wuqf.xiaokuo.remoting;

import java.net.InetSocketAddress;

/**
 * Created by wuqf on 17-2-24.
 */
public interface Channel extends EndPoint {

    InetSocketAddress getRemoteAddress();

    boolean isConnected();

    boolean hasAttribute(String key);

    Object getAttribute(String key);

    void setAttribute(String key, Object value);

    void removeAttribute(String key);
}
