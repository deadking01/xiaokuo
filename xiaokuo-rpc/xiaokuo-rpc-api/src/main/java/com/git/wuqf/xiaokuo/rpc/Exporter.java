package com.git.wuqf.xiaokuo.rpc;

/**
 * Created by wuqf on 17-3-11.
 */
public interface Exporter<T> {

    Invoker<T> getInvoker();

    void unexport();
}
