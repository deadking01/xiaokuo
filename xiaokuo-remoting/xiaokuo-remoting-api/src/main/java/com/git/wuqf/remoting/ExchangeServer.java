package com.git.wuqf.remoting;

import com.git.wuqf.remoting.exchange.ExchangeChannel;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * Created by wuqf on 17-3-18.
 */
public interface ExchangeServer extends Server {

    Collection<ExchangeChannel> getExchangeChannels();

    ExchangeChannel getExchangeChannel(InetSocketAddress remoteAddress);
}
