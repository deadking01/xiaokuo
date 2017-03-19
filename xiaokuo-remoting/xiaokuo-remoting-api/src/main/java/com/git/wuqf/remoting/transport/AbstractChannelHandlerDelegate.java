package com.git.wuqf.remoting.transport;


import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.RemotingException;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public abstract class AbstractChannelHandlerDelegate implements ChannelHandlerDelegate {

    protected ChannelHandler handler;

    protected AbstractChannelHandlerDelegate(ChannelHandler handler) {
        this.handler = handler;
    }

    public ChannelHandler getHandler() {
        if (handler instanceof ChannelHandlerDelegate) {
            return ((ChannelHandlerDelegate)handler).getHandler();
        }
        return handler;
    }

    public void connected(Channel channel)  {
        handler.connected(channel);
    }

    public void disconnected(Channel channel)  {
        handler.disconnected(channel);
    }

    public void sent(Channel channel, Object message) throws RemotingException {
        handler.sent(channel, message);
    }

    public void received(Channel channel, Object message) throws RemotingException {
        handler.received(channel, message);
    }

    public void caught(Channel channel, Throwable exception) throws RemotingException {
        handler.caught(channel, exception);
    }
}
