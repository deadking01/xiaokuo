package com.git.wuqf.rpc.support;

/**
 * Created by wuqf on 17-3-18.
 */
public class ProtocolUtil {
    public static String serviceKey(int port,String serviceName,String serviceVersion,String serviceGroup){
        StringBuilder sb=new StringBuilder();
        sb.append(serviceGroup).append("/").append(serviceName).append(":").append(port);
        return sb.toString();
    }
}
