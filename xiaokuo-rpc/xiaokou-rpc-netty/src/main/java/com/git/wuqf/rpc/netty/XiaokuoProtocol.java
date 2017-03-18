package com.git.wuqf.rpc.netty;

import com.git.wuqf.remoting.ExchangeClient;
import com.git.wuqf.remoting.ExchangeServer;
import com.git.wuqf.remoting.exchange.ExchangeHandler;
import com.git.wuqf.remoting.exchange.Exchangers;
import com.git.wuqf.rpc.Exporter;
import com.git.wuqf.rpc.Invoker;
import com.git.wuqf.rpc.protocol.AbstractProtocol;
import com.git.wuqf.xiaokuo.common.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wuqf on 17-3-18.
 */
public class XiaokuoProtocol extends AbstractProtocol{

    public static String NAME="xiaokuo";
    public static int DEFAULT_PORT=20880;

    private final Map<String,ExchangeServer> servers=new ConcurrentHashMap<>();
    private final Map<String,ExchangeClient> clients=new ConcurrentHashMap<>();

    private ExchangeHandler requestHandler;

    @Override
    public int getDefaultPort() {
        return DEFAULT_PORT;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) {

        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) {
        XiaokuoInvoker invoker=new XiaokuoInvoker(type,url,getClients(url),invokers);
        return invoker;
    }


    private ExchangeClient[] getClients(URL url){
        ExchangeClient[] clients=new ExchangeClient[1];
        for(int i=0;i<clients.length;i++){
            clients[i]=initClient(url);
        }
        return clients;
    }

    private ExchangeClient initClient(URL url) {
        ExchangeClient client;
        client= Exchangers.connect(url,requestHandler);
        return client;
    }
}
