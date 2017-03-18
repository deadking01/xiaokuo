package com.git.wuqf.xiaokuo.remoting.zookeeper;

import com.git.wuqf.xiaokuo.common.URL;
import java.util.List;

/**
 * Created by sdzn-dsj on 2016/12/16.
 */
public interface ZookeeperClient {
    void create(String path,boolean ephemeral);
    void delete(String path);
    List<String> getChildren(String path);
    List<String> addChildrenListener(String path,ChildListener listener);
    void removeChildListener(String path,ChildListener childListener);
    void addStateListener(StateListener stateListener);
    void removeStateListener(StateListener stateListener);
    boolean isConnected();
    void close();
    URL getUrl();
}
