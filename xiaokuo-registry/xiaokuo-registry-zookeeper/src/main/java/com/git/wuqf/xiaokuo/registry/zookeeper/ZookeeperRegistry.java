package com.git.wuqf.xiaokuo.registry.zookeeper;

import com.git.wuqf.xiaokuo.registry.NotifyListener;
import com.git.wuqf.xiaokuo.registry.support.AbstractRegistry;
import com.github.zkclient.IZkClient;
import com.github.zkclient.ZkClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public class ZookeeperRegistry extends AbstractRegistry {

    private final static Logger logger = LoggerFactory.getLogger(ZookeeperRegistry.class);

    private final static String DEFAULT_REGISTRY_PATH = "/xiaokuo/registry/data";
    private final IZkClient zkClient;
    private CountDownLatch latch = new CountDownLatch(1);

    public ZookeeperRegistry(URL url, String zookeeperConnectionString) {
        super(url);
        zkClient = new ZkClient(zookeeperConnectionString);
        zkClient.connect(10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                latch.countDown();
            }
        });
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void destory() {

    }

    @Override
    public void register(URL serviceUrl) {
        String path = serviceUrl.getPath();
        if(StringUtils.isEmpty(path)){
            path=DEFAULT_REGISTRY_PATH;
        }
        String serviceInfo=serviceUrl.getHost()+":"+String.valueOf(serviceUrl.getPort());
        byte[] data =serviceInfo.getBytes();

        zkClient.createPersistent(path, true);
        zkClient.writeData(path, data);
        logger.debug("create zookeeper node ({} => {})", path, data);
    }

    @Override
    public void unregister(URL url) {
        zkClient.delete(url.getPath());
    }

    @Override
    public void subscribe(URL url, NotifyListener listener) {

    }

    @Override
    public void unsubscribe(URL url, NotifyListener listener) {

    }

    @Override
    public List<URL> lookup(URL url) {
        String path=url.getPath();
        if(StringUtils.isEmpty(path)){
            path=DEFAULT_REGISTRY_PATH;
        }
        byte[] bs = zkClient.readData(path);
        String value = new String(bs);

        URL u = new URL(value);
        List urls = new ArrayList<>();
        urls.add(u);
        return urls;
    }

}
