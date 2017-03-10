package com.git.wuqf.remoting.netty;

import com.git.wuqf.remoting.Client;
import com.git.wuqf.remoting.Server;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wuqf on 17-2-25.
 */
public class NettyTransportTest {

    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("http://localhost:9999");
        NettyTransports nettyTransports=new NettyTransports();

        Server server=nettyTransports.bind(url,new WordHandler());
        Client client=nettyTransports.Connect(url,new WordHandler());
        client.send(new Word("aaa"));
    }
}
