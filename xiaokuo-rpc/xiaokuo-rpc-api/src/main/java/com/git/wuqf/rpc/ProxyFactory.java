package com.git.wuqf.rpc;

import com.git.wuqf.remoting.RpcException;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.extension.SPI;

/**
 * Created by wuqf on 17-3-25.
 */
@SPI("javassist")
public interface ProxyFactory {

    /**
     * create proxy.
     *
     * @param invoker
     * @return proxy
     */
    <T> T getProxy(Invoker<T> invoker) throws RpcException;

    /**
     * create invoker.
     *
     * @param <T>
     * @param proxy
     * @param type
     * @param url
     * @return invoker
     */
    <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException;
}
