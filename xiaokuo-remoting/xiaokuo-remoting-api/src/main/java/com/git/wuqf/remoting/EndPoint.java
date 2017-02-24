package com.git.wuqf.remoting;

import java.net.InetSocketAddress;

/**
 * Created by wuqf on 17-2-24.
 */
public interface EndPoint {
    String getUrl();
    String getChannelHandler();
    InetSocketAddress getLocalAddress();

    /**
     *
     * @param message
     * @param sent 消息是否发送完成
     */
    void send(Object message,boolean sent);
    void send(Object message);

    void close();
    void close(int timeout);
    boolean isClosed();

}
