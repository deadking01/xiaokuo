package com.git.wuqf.xiaokuo.common;

import com.git.wuqf.xiaokuo.common.URL;

/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public interface Node {

    URL getUrl();

    boolean isAvailable();

    void destory();
}
