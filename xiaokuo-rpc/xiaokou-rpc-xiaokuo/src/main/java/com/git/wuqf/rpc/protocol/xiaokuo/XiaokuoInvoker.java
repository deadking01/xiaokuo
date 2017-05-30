package com.git.wuqf.rpc.protocol.xiaokuo;

import com.git.wuqf.remoting.ExchangeClient;
import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.rpc.*;
import com.git.wuqf.rpc.protocol.AbstractInvoker;
import com.git.wuqf.xiaokuo.common.Constants;
import com.git.wuqf.xiaokuo.common.URL;

import java.util.Set;

/**
 * Created by wuqf on 17-3-11.
 */
public class XiaokuoInvoker<T> extends AbstractInvoker {

    private final ExchangeClient[] clients;
    private final Set<Invoker<?>> invokers;
    private final String version;

    public XiaokuoInvoker(Class<T> serviceType, URL url, ExchangeClient[] clients, Set<Invoker<?>> invokers) {
        super(serviceType, url, new String[]{Constants.INTERFACE_KEY, Constants.GROUP_KEY, Constants.TOKEN_KEY, Constants.TIMEOUT_KEY});
        this.clients = clients;
        // get version.
        this.version = url.getParameter(Constants.VERSION_KEY, "0.0.0");
        this.invokers = invokers;
    }

    @Override
    protected Result doInvoke(Invocation invocation) throws RemotingException {
        RpcInvocation inv = (RpcInvocation) invocation;
        final String methodName = inv.getMethodName();
        ExchangeClient currentClient = clients[0];
        currentClient.send(inv);
        int timeout = getUrl().getMethodParameter(methodName, Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
        RpcContext.getContext().setFuture(null);
        return (Result) currentClient.request(inv, timeout).get();
    }

    @Override
    public void destory() {
        return;
    }
}
