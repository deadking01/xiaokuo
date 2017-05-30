package com.git.wuqf.xiaokuo.remoting.netty;

import java.io.Serializable;

/**
 * Created by wuqf on 17-3-11.
 */
public class Word implements Serializable {
    public Word(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
