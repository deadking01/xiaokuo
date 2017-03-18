package com.git.wuqf.xiaokuo.registry.support;

import com.git.wuqf.xiaokuo.registry.Registry;
import com.git.wuqf.xiaokuo.registry.RegistryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.git.wuqf.xiaokuo.common.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public abstract class AbstractRegistryFactory implements RegistryFactory{
    private static final Logger logger= LoggerFactory.getLogger(AbstractRegistry.class);
    private static final ReentrantLock lock=new ReentrantLock();
    private static final Map<String,Registry> registries=new ConcurrentHashMap<>();

    /**
     * 获取所有注册中心
     *
     * @return 所有注册中心
     */
    public static Collection<Registry> getRegistries(){
        return Collections.unmodifiableCollection(registries.values());
    }

    public static void destoryAll(){
        logger.info("Close all registries "+getRegistries());
        lock.lock();
        try{
            for(Registry registry:getRegistries()){
                registry.destory();
            }
            registries.clear();
        }finally {
            lock.unlock();
        }
    }
    protected abstract Registry createRegistry(URL url);
}
