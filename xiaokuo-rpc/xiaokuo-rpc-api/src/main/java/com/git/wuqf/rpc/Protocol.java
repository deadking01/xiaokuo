package com.git.wuqf.rpc;

import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-3-11.
 */
public interface Protocol {
    int getDefaultPort();

    <T> Exporter<T> export(Invoker<T> invoker) throws RemotingException;

    <T> Invoker<T> refer(Class<T> type, URL url) throws RemotingException;

    void destory();
}
