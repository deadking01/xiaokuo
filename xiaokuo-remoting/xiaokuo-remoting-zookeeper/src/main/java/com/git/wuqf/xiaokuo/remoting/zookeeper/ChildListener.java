package com.git.wuqf.xiaokuo.remoting.zookeeper;

import java.util.List;

/**
 * Created by sdzn-dsj on 2016/12/16.
 */
public interface ChildListener {
    void childChanged(String path,List<String> children);
}
