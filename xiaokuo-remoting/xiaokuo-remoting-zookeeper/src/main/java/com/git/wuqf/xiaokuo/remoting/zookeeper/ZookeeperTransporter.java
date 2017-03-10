package com.git.wuqf.xiaokuo.remoting.zookeeper;


/**
 * Created by sdzn-dsj on 2016/12/16.
 */
public interface ZookeeperTransporter {
    ZookeeperClient coonect(URL url);
}
