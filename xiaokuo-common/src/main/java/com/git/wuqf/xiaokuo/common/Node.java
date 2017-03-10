package com.git.wuqf.xiaokuo.common;

import java.net.URL;

/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public interface Node {

    URL getUrl();

    boolean isAvailable();

    void destory();
}
