package com.git.wuqf.rpc.protocol;

import com.git.wuqf.rpc.Invocation;
import com.git.wuqf.rpc.Invoker;
import com.git.wuqf.rpc.Result;
import com.git.wuqf.rpc.RpcInvocation;

import com.git.wuqf.xiaokuo.common.URL;
import java.util.Map;

/**
 * Created by wuqf on 17-3-11.
 */
public abstract class AbstractInvoker<T> implements Invoker{
    private final Class<T> type;
    private final URL url;
    private final Map<String,String> attachment;
    private volatile boolean available=true;
    private volatile boolean destoryed=false;


    public AbstractInvoker(Class<T> type, URL url, Map<String, String> attachment) {
        this.type=type;
        this.url=url;
        this.attachment=attachment;
    }

    public Class<T> getInterface(){
        return type;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public Result invoke(Invocation inv){
        RpcInvocation invocation = (RpcInvocation) inv;
        return doInvoke(invocation);
    }
    protected abstract Result doInvoke(Invocation invocation);
}
