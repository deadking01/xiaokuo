package com.git.wuqf.rpc.proxy.javassist;

import com.git.wuqf.rpc.Invoker;
import com.git.wuqf.rpc.proxy.AbstractProxyFactory;
import com.git.wuqf.rpc.proxy.AbstractProxyInvoker;
import com.git.wuqf.rpc.proxy.InvokerInvocationHandler;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.bytecode.Proxy;
import com.git.wuqf.xiaokuo.common.bytecode.Wrapper;

/**
 * Created by wuqf on 17-3-25.
 */
public class JavassistProxyFactory extends AbstractProxyFactory {

    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.getProxy(interfaces).newInstance(new InvokerInvocationHandler(invoker));
    }

    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) {

        final Wrapper wrapper = Wrapper.getWrapper(proxy.getClass().getName().indexOf('$') < 0 ? proxy.getClass() : type);
        return new AbstractProxyInvoker<T>(proxy, type, url) {

            @Override
            public void destory() {

            }

            @Override
            protected Object doInvoke(T proxy, String methodName,
                                      Class<?>[] parameterTypes,
                                      Object[] arguments) throws Throwable {
                return wrapper.invokeMethod(proxy, methodName, parameterTypes, arguments);
            }
        };
    }
}
