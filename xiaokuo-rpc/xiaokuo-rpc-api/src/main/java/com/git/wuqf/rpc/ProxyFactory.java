package com.git.wuqf.rpc;

import com.git.wuqf.remoting.RpcException;
import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-3-25.
 */
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
