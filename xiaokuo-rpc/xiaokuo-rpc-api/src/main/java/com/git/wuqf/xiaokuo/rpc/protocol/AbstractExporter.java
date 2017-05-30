package com.git.wuqf.xiaokuo.rpc.protocol;

import com.git.wuqf.xiaokuo.rpc.Exporter;
import com.git.wuqf.xiaokuo.rpc.Invoker;

/**
 * Created by wuqf on 17-3-11.
 */
public abstract class AbstractExporter<T> implements Exporter {

    private final Invoker<T> invoker;

    private volatile boolean unexported = false;

    public AbstractExporter(Invoker<T> invoker) {
        this.invoker = invoker;
    }

    @Override
    public Invoker<T> getInvoker() {
        return invoker;
    }

    @Override
    public void unexport() {
        if (unexported) {
            return;
        }
        unexported = true;
        getInvoker().destory();
    }

    @Override
    public String toString() {
        return getInvoker().toString();
    }
}
