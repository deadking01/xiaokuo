package com.git.wuqf.remoting.exchange;

import com.git.wuqf.remoting.ExchangeClient;
import com.git.wuqf.remoting.ExchangeServer;

import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-3-18.
 */
public interface Exchanger {

    ExchangeServer bind(URL url, ExchangeHandler handler);

    ExchangeClient connect(URL url, ExchangeHandler handler);
}
