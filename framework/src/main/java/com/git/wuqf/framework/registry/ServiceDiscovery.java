package com.git.wuqf.framework.registry;

import com.git.wuqf.framework.constant.ZKConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sdzn-dsj on 2016/12/13.
 */
public class ServiceDiscovery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDiscovery.class);
    private String registryAddress;
    private ZookeeperUtil zookeeperUtil;

    public ServiceDiscovery(String registryAddress) {
        this.registryAddress = registryAddress;
        zookeeperUtil=new ZookeeperUtil(registryAddress);
    }

    public String discover() {
        byte[] data=zookeeperUtil.readNode(ZKConstant.ZK_DATA_PATH);
        String url=new String(data);
        return url;
    }
}
