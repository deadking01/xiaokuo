package com.git.wuqf.xiaokuo.remoting.zookeeper;

/**
 * Created by sdzn-dsj on 2016/12/16.
 */
public interface StateListener {
    int DISCONNECTED=0;
    int CONNECTED=1;
    int RECONNECTED=2;
    void stateChanged(int connected);
}
