package com.git.wuqf.remoting.exchange;

import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.ExchangeClient;
import com.git.wuqf.remoting.ExchangeServer;
import com.git.wuqf.remoting.support.ExchangeHandlerDispatcher;
import com.git.wuqf.remoting.support.Replier;
import com.git.wuqf.remoting.transport.ChannelHandlerAdapter;
import com.git.wuqf.xiaokuo.common.Constants;
import com.git.wuqf.xiaokuo.common.URL;


/**
 * Created by wuqf on 17-3-18.
 */
public class Exchangers {
    public static ExchangeServer bind(String url, Replier<?> replier)  {
        return bind(URL.valueOf(url), replier);
    }

    public static ExchangeServer bind(URL url,  Replier<?> replier)  {
        return bind(url, new ChannelHandlerAdapter(), replier);
    }

    public static ExchangeServer bind(String url, ChannelHandler handler, Replier<?> replier)  {
        return bind(URL.valueOf(url), handler, replier);
    }

    public static ExchangeServer bind(URL url, ChannelHandler handler, Replier<?> replier)  {
        return bind(url, new ExchangeHandlerDispatcher(replier, handler));
    }

    public static ExchangeServer bind(String url, ExchangeHandler handler)  {
        return bind(URL.valueOf(url), handler);
    }

    public static ExchangeServer bind(URL url, ExchangeHandler handler)  {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        url = url.addParameterIfAbsent(Constants.CODEC_KEY, "exchange");
        return getExchanger(url).bind(url, handler);
    }

    public static ExchangeClient connect(String url)  {
        return connect(URL.valueOf(url));
    }

    public static ExchangeClient connect(URL url)  {
        return connect(url, new ChannelHandlerAdapter(), null);
    }

    public static ExchangeClient connect(String url, Replier<?> replier)  {
        return connect(URL.valueOf(url), new ChannelHandlerAdapter(), replier);
    }

    public static ExchangeClient connect(URL url, Replier<?> replier)  {
        return connect(url, new ChannelHandlerAdapter(), replier);
    }

    public static ExchangeClient connect(String url, ChannelHandler handler, Replier<?> replier)  {
        return connect(URL.valueOf(url), handler, replier);
    }

    public static ExchangeClient connect(URL url, ChannelHandler handler, Replier<?> replier)  {
        return connect(url, new ExchangeHandlerDispatcher(replier, handler));
    }

    public static ExchangeClient connect(String url, ExchangeHandler handler)  {
        return connect(URL.valueOf(url), handler);
    }

    public static ExchangeClient connect(URL url, ExchangeHandler handler)  {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        url = url.addParameterIfAbsent(Constants.CODEC_KEY, "exchange");
        return getExchanger(url).connect(url, handler);
    }

    public static Exchanger getExchanger(URL url) {
        String type = url.getParameter(Constants.EXCHANGER_KEY, Constants.DEFAULT_EXCHANGER);
        return null;
    }



    private Exchangers(){
    }

}
