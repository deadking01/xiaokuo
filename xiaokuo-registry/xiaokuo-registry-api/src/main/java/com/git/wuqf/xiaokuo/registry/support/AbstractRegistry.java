package com.git.wuqf.xiaokuo.registry.support;

import com.git.wuqf.xiaokuo.common.utils.NamedThreadFactory;
import com.git.wuqf.xiaokuo.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import com.git.wuqf.xiaokuo.common.URL;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public abstract class AbstractRegistry implements Registry {
    protected final Logger logger= LoggerFactory.getLogger(getClass());

    private static final char URL_SEPARATOR=' ';
    private static final String URL_SPLIT="\\s+";
    private URL registryUrl;
    private File file;

    private final Properties properties=new Properties();

    private final ExecutorService registryCacheExecutor= Executors.newFixedThreadPool(1,new NamedThreadFactory("XiaokuoSaveRegistryCache",true));

    public AbstractRegistry(URL url) {
        setUrl(url);
    }


    protected void setUrl(URL url){
        if(url==null){
            throw new IllegalArgumentException("registry url == null");
        }
        this.registryUrl=url;
    }
    public URL getUrl(){
        return registryUrl;
    }
}
