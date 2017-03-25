package com.git.wuqf.remoting.exchange.support;

import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.remoting.exchange.ExchangeChannel;
import com.git.wuqf.remoting.exchange.ExchangeHandler;
import com.git.wuqf.remoting.telnet.support.TelnetHandlerAdapter;

/**
 * Created by wuqf on 17-3-25.
 */
public abstract class ExchangeHandlerAdapter extends TelnetHandlerAdapter implements ExchangeHandler {

    public Object reply(ExchangeChannel channel, Object msg) throws RemotingException {
        return null;
    }

}
