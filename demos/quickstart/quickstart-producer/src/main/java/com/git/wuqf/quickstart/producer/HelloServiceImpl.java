package com.git.wuqf.quickstart.producer;


import com.git.wuqf.framework.annotation.RpcService;
import com.git.wuqf.quickstart.api.HelloService;
import com.git.wuqf.quickstart.api.Person;

/**
 * Created by sdzn-dsj on 2016/12/13.
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService{
    public String hello(String msg) {
        return "hello"+msg;
    }

    public Person grow(Person person) {
        person.setAge(person.getAge()+1);
        return person;
    }
}
