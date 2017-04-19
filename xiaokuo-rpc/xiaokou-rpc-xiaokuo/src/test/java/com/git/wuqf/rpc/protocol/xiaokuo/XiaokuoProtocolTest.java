package com.git.wuqf.rpc.protocol.xiaokuo;

import com.git.wuqf.rpc.Protocol;
import com.git.wuqf.rpc.ProxyFactory;
import com.git.wuqf.rpc.protocol.xiaokuo.support.DemoService;
import com.git.wuqf.rpc.protocol.xiaokuo.support.DemoServiceImpl;
import com.git.wuqf.rpc.proxy.javassist.JavassistProxyFactory;
import com.git.wuqf.xiaokuo.common.URL;
import com.git.wuqf.xiaokuo.common.extension.ExtensionLoader;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wuqf on 17-3-25.
 */
public class XiaokuoProtocolTest {
    private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

    @Test
    public void testDemoProtocol() throws Exception
    {
        DemoService service = new DemoServiceImpl();
        protocol.export(proxy.getInvoker(service, DemoService.class, URL.valueOf("xiaokuo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));
        service = proxy.getProxy(protocol.refer(DemoService.class, URL.valueOf("xiaokuo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));

        int size=service.getSize(new String[]{"", "", ""});

        assertEquals(size, 3);
    }
}
