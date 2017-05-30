package com.git.wuqf.xiaokuo.registry.zookeeper;

import com.git.wuqf.xiaokuo.registry.Registry;
import com.git.wuqf.xiaokuo.registry.support.AbstractRegistryFactory;

import com.git.wuqf.xiaokuo.common.URL;


/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public class ZookeeperRegistryFactory extends AbstractRegistryFactory {
    private String zookeeperConnectionString;

    public ZookeeperRegistryFactory(String zookeeperConnectionString) {
        this.zookeeperConnectionString = zookeeperConnectionString;
    }

    @Override
    public Registry getRegistry(URL url) {
        return new ZookeeperRegistry(url, zookeeperConnectionString);
    }

    @Override
    protected Registry createRegistry(URL url) {
        return new ZookeeperRegistry(url, zookeeperConnectionString);
    }

    public String getZookeeperConnectionString() {
        return zookeeperConnectionString;
    }

    public void setZookeeperConnectionString(String zookeeperConnectionString) {
        this.zookeeperConnectionString = zookeeperConnectionString;
    }
}
