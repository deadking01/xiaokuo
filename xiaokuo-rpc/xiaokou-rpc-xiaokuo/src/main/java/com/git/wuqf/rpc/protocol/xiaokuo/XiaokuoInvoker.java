package com.git.wuqf.rpc.protocol.xiaokuo;

import com.git.wuqf.remoting.ExchangeClient;
import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.rpc.*;
import com.git.wuqf.rpc.protocol.AbstractInvoker;

import com.git.wuqf.xiaokuo.common.URL;
import java.util.Set;

/**
 * Created by wuqf on 17-3-11.
 */
public class XiaokuoInvoker<T> extends AbstractInvoker {

    private final ExchangeClient[] clients;
    private final Set<Invoker<?>> invokers;


    public XiaokuoInvoker(Class<T> serviceType, URL url, ExchangeClient[] clients, Set<Invoker<?>> invokers){
        super(serviceType, url,null);
        this.clients = clients;
        this.invokers = invokers;
    }
    @Override
    protected Result doInvoke(Invocation invocation) throws RemotingException {
        RpcInvocation inv=(RpcInvocation)invocation;
        final String methodName=inv.getMethodName();
        ExchangeClient currentClient=clients[0];
        currentClient.send(inv);

        return new RpcResult();
    }

    @Override
    public void destory() {
        return;
    }
}
