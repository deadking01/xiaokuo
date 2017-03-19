package com.git.wuqf.xiaokuo.registry.zookeeper;

import com.git.wuqf.remoting.RpcException;
import com.git.wuqf.xiaokuo.common.Constants;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.utils.UrlUtils;
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
    private final static int DEFAULT_ZOOKEEPER_PORT = 2181;
    private final String        root;

    public ZookeeperRegistry(URL url, String zookeeperConnectionString) {
        super(url);
        zkClient = new ZkClient(zookeeperConnectionString);
        zkClient.connect(10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                latch.countDown();
            }
        });
        this.root=DEFAULT_REGISTRY_PATH;
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
        if (url == null) {
            throw new IllegalArgumentException("lookup url == null");
        }
        try {
            List<String> providers = new ArrayList<String>();
            for (String path : toCategoriesPath(url)) {
                List<String> children = zkClient.getChildren(path);
                if (children != null) {
                    providers.addAll(children);
                }
            }
            return toUrlsWithoutEmpty(url, providers);
        } catch (Throwable e) {
            throw new RpcException("Failed to lookup " + url + " from zookeeper " + getUrl() + ", cause: " + e.getMessage(), e);
        }
    }
    private String[] toCategoriesPath(URL url) {
        String[] categroies;
        if (Constants.ANY_VALUE.equals(url.getParameter(Constants.CATEGORY_KEY))) {
            categroies = new String[] {Constants.PROVIDERS_CATEGORY, Constants.CONSUMERS_CATEGORY,
                    Constants.ROUTERS_CATEGORY, Constants.CONFIGURATORS_CATEGORY};
        } else {
            categroies = url.getParameter(Constants.CATEGORY_KEY, new String[] {Constants.DEFAULT_CATEGORY});
        }
        String[] paths = new String[categroies.length];
        for (int i = 0; i < categroies.length; i ++) {
            paths[i] = toServicePath(url) + Constants.PATH_SEPARATOR + categroies[i];
        }
        return paths;
    }

    private String toCategoryPath(URL url) {
        return toServicePath(url) + Constants.PATH_SEPARATOR + url.getParameter(Constants.CATEGORY_KEY, Constants.DEFAULT_CATEGORY);
    }

    private String toUrlPath(URL url) {
        return toCategoryPath(url) + Constants.PATH_SEPARATOR + URL.encode(url.toFullString());
    }

    private List<URL> toUrlsWithoutEmpty(URL consumer, List<String> providers) {
        List<URL> urls = new ArrayList<URL>();
        if (providers != null && providers.size() > 0) {
            for (String provider : providers) {
                provider = URL.decode(provider);
                if (provider.contains("://")) {
                    URL url = URL.valueOf(provider);
                    if (UrlUtils.isMatch(consumer, url)) {
                        urls.add(url);
                    }
                }
            }
        }
        return urls;
    }

    private List<URL> toUrlsWithEmpty(URL consumer, String path, List<String> providers) {
        List<URL> urls = toUrlsWithoutEmpty(consumer, providers);
        if (urls == null || urls.isEmpty()) {
            int i = path.lastIndexOf('/');
            String category = i < 0 ? path : path.substring(i + 1);
            URL empty = consumer.setProtocol(Constants.EMPTY_PROTOCOL).addParameter(Constants.CATEGORY_KEY, category);
            urls.add(empty);
        }
        return urls;
    }

    static String appendDefaultPort(String address) {
        if (address != null && address.length() > 0) {
            int i = address.indexOf(':');
            if (i < 0) {
                return address + ":" + DEFAULT_ZOOKEEPER_PORT;
            } else if (Integer.parseInt(address.substring(i + 1)) == 0) {
                return address.substring(0, i + 1) + DEFAULT_ZOOKEEPER_PORT;
            }
        }
        return address;
    }
    private String toServicePath(URL url) {
        String name = url.getServiceInterface();
        if (Constants.ANY_VALUE.equals(name)) {
            return toRootPath();
        }
        return toRootDir() + URL.encode(name);
    }
    private String toRootDir() {
        if (root.equals(Constants.PATH_SEPARATOR)) {
            return root;
        }
        return root + Constants.PATH_SEPARATOR;
    }

    private String toRootPath() {
        return root;
    }

}
