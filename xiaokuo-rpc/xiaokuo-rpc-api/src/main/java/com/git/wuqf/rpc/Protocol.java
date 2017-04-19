package com.git.wuqf.rpc;

import com.git.wuqf.remoting.RemotingException;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.extension.Adaptive;
import com.git.wuqf.xiaokuo.common.extension.SPI;

/**
 * Created by wuqf on 17-3-11.
 */
@SPI("xiaokuo")
public interface Protocol {
    int getDefaultPort();

    @Adaptive
    <T> Exporter<T> export(Invoker<T> invoker) throws RemotingException;

    @Adaptive
    <T> Invoker<T> refer(Class<T> type, URL url) throws RemotingException;

    void destory();
}
