package com.git.wuqf.quickstart.consumer;


import com.git.wuqf.framework.client.RpcProxy;
import com.git.wuqf.quickstart.api.HelloService;
import com.git.wuqf.quickstart.api.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sdzn-dsj on 2016/12/13.
 */
public class SpringRpcConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        RpcProxy rpcProxy = (RpcProxy) context.getBean("rpcProxy");
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println(result);

        Person person=new Person("wuqf",1);
        person=helloService.grow(person);
        System.out.println(person);
    }
}
