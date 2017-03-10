package com.git.wuqf.remoting.netty;

import com.git.wuqf.remoting.Client;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wuqf on 17-2-25.
 */
public class NettyClientTest {
    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("http://localhost:9999");
        NettyTransports nettyTransports=new NettyTransports();
        Client client=nettyTransports.Connect(url,new WordHandler());
        for(int i=0;i<2;i++){
            client.send(new Word("aaa"));
        }
        client.close();
    }
}
