package com.git.wuqf.rpc.protocol;

import com.git.wuqf.rpc.Exporter;
import com.git.wuqf.rpc.Invoker;
import com.git.wuqf.rpc.Protocol;
import com.git.wuqf.rpc.support.ProtocolUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by wuqf on 17-3-11.
 */
public abstract class AbstractProtocol implements Protocol {

    protected final Map<String,Exporter<?>> exporters =new ConcurrentHashMap<>();
    protected final Set<Invoker<?>> invokers=new ConcurrentSkipListSet<>();

    protected String serviceKey(int port ,String serviceName,String serviceVersion,String serviceGroup){
        return ProtocolUtil.serviceKey(port,serviceName,serviceVersion,serviceGroup);
    }

    @Override
    public void destory() {
        for(Invoker<?> invoker:invokers){
            if(invoker!=null){
                invokers.remove(invoker);
            }
        }
        for(String key: exporters.keySet()){
            Exporter<?> exporter=exporters.remove(key);
            if(exporter!=null){
                exporter.unexport();
            }
        }
    }
}
