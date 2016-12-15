package com.git.wuqf.xiaokuo.common;

import java.util.Map;

/**
 * Created by sdzn-dsj on 2016/12/15.
 */
public class URL {


    private  String host;
    private  int port;
    private  String path;
    private byte[] data;

    public URL(String host,int port,String path,byte[] data){
        this.host=host;
        this.port=port;
        this.path=path;
        this.data=data;
    }
    public URL(String url){
        this.host=url.split(":")[0];
        this.port=Integer.valueOf(url.split(":")[1]);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getConnectionString(){
        return this.host+":"+this.port;
    }
}
