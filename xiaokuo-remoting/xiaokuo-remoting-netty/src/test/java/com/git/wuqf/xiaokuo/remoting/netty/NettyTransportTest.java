package com.git.wuqf.xiaokuo.remoting.netty;

import com.git.wuqf.xiaokuo.remoting.Client;
import com.git.wuqf.xiaokuo.remoting.RemotingException;
import com.git.wuqf.xiaokuo.remoting.Server;
import com.git.wuqf.xiaokuo.common.URL;

import java.net.MalformedURLException;

/**
 * Created by wuqf on 17-2-25.
 */
public class NettyTransportTest {

    public static void main(String[] args) throws MalformedURLException, RemotingException {
        URL url = new URL("xiaokuo", "localhost", 9999);
        NettyTransporter nettyTransports = new NettyTransporter();

        Server server = nettyTransports.bind(url, new WordHandler());
        Client client = nettyTransports.connect(url, new WordHandler());
        client.send(new Word("aaa"));
    }
}
