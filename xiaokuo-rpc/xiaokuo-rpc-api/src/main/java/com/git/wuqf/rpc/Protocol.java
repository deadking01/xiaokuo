package com.git.wuqf.rpc;

import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by wuqf on 17-3-11.
 */
public interface Protocol {
    int getDefaultPort();

    <T> Exporter<T> export(Invoker<T> invoker);

    <T> Invoker<T> refer(Class<T> type, URL url);

    void destory();
}
