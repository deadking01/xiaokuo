package com.git.wuqf.remoting;

/**
 * Created by wuqf on 17-2-24.
 */
public interface ChannelHandler {

    /**
     * on channel connected
     *
     * @param channel
     */
    void connected(Channel channel);

    /**
     * one channel disconnected
     *
     * @param channel
     */
    void disConnected(Channel channel);

    /**
     * on message sent
     *
     * @param channel
     * @param message
     */
    void sent(Channel channel, Object message);

    /**
     * on message received
     *
     * @param channel
     * @param message
     */
    void received(Channel channel, Object message);

    /**
     * on exception caught
     *
     * @param channel
     * @param exception
     */
    void caught(Channel channel, Throwable exception);
}
