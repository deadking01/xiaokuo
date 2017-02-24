package com.git.wuqf.remoting;

/**
 * Created by wuqf on 17-2-24.
 */
public interface Channel {

    boolean isConnected();

    boolean hasAttribute(String key);

    Object getAttribute(String key);

    void setAttribute(String key, Object value);

    void removeAttribute(String key);
}
