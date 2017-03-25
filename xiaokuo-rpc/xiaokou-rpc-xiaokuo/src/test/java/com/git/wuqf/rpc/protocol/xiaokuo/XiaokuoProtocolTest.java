package com.git.wuqf.rpc.protocol.xiaokuo;

import com.git.wuqf.rpc.Protocol;
import com.git.wuqf.rpc.ProxyFactory;
import com.git.wuqf.rpc.protocol.xiaokuo.support.DemoService;
import com.git.wuqf.rpc.protocol.xiaokuo.support.DemoServiceImpl;
import com.git.wuqf.rpc.proxy.javassist.JavassistProxyFactory;
import com.git.wuqf.xiaokuo.common.URL;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wuqf on 17-3-25.
 */
public class XiaokuoProtocolTest {
    private Protocol protocol = new XiaokuoProtocol();
    private ProxyFactory proxy = new JavassistProxyFactory();

    @Test
    public void testDemoProtocol() throws Exception
    {
        DemoService service = new DemoServiceImpl();
        protocol.export(proxy.getInvoker(service, DemoService.class, URL.valueOf("dubbo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));
        service = proxy.getProxy(protocol.refer(DemoService.class, URL.valueOf("dubbo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));
        assertEquals(service.getSize(new String[]{"", "", ""}), 3);
    }
}
