package com.git.wuqf.framework.registry;

import com.github.zkclient.IZkClient;
import com.github.zkclient.ZkClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sdzn-dsj on 2016/12/14.
 */
public class ZookeeperUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperUtil.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private IZkClient client;

    public  ZookeeperUtil(String registryAddress) {
        if(client ==null) {
            client = new ZkClient(registryAddress);
            client.connect(10000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
        }
    }

    public void createNode( String path,String data) {

        byte[] bytes = data.getBytes();
        client.createPersistent(path,true);
        client.writeData(path, bytes);
        logger.debug("create zookeeper node ({} => {})", path, data);
    }

    public byte[] readNode(String path){
        byte[] value= client.readData(path);
        return value;
    }
}
