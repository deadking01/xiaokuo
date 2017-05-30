package com.git.wuqf.xiaokuo.remoting.exchange;

import com.git.wuqf.xiaokuo.remoting.ExchangeClient;
import com.git.wuqf.xiaokuo.remoting.ExchangeServer;

import com.git.wuqf.xiaokuo.remoting.RemotingException;
import com.git.wuqf.xiaokuo.remoting.exchange.support.header.HeaderExchanger;
import com.git.wuqf.xiaokuo.common.Constants;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.extension.Adaptive;
import com.git.wuqf.xiaokuo.common.extension.SPI;

/**
 * Created by wuqf on 17-3-18.
 */
@SPI(HeaderExchanger.NAME)
public interface Exchanger {

    @Adaptive({Constants.EXCHANGER_KEY})
    ExchangeServer bind(URL url, ExchangeHandler handler) throws RemotingException;

    @Adaptive({Constants.EXCHANGER_KEY})
    ExchangeClient connect(URL url, ExchangeHandler handler) throws RemotingException;
}
