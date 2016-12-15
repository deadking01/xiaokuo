package com.git.wuqf.framework.client;

import com.git.wuqf.framework.RpcRequest;
import com.git.wuqf.framework.RpcResponse;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.registry.RegistryFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * Created by sdzn-dsj on 2016/12/13.
 */
public class RpcProxy {

    private URL registryUrl;
    private RegistryFactory registryFactory;

    public RpcProxy(URL registryUrl, RegistryFactory registryFactory) {
        this.registryUrl = registryUrl;
        this.registryFactory = registryFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);

                        RpcResponse response = null;
                        if (registryFactory != null) {
                            URL serviceUrl = registryFactory.getRegistry(registryUrl).lookup(registryUrl).get(0); // 发现服务
                            RpcClient client = new RpcClient(serviceUrl.getHost(), serviceUrl.getPort()); // 初始化 RPC 客户端
                            response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应
                            if (response.isError()) {
                                throw response.getError();
                            } else {
                                return response.getResult();
                            }
                        }
                        throw response.getError();
                    }
                }
        );
    }
}

