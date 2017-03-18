package com.git.wuqf.remoting;

import java.net.SocketAddress;

/**
 * Created by wuqf on 17-2-24.
 */
public interface EndPoint {
    com.git.wuqf.xiaokuo.common.URL getUrl();

    ChannelHandler getChannelHandler();

    SocketAddress getLocalAddress();

    /**
     * @param message
     * @param sent    消息是否发送完成
     */
    void send(Object message, boolean sent);

    void send(Object message);

    void close();

    void close(int timeout);

    boolean isClosed();

}
