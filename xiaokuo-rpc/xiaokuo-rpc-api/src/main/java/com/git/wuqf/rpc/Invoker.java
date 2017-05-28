package com.git.wuqf.rpc;

import com.git.wuqf.remoting.RpcException;
import com.git.wuqf.xiaokuo.common.Node;

/**
 * Created by wuqf on 17-3-11.
 */
public interface Invoker<T> extends Node {

    Class<T> getInterface();

    Result invoke(Invocation invocation) throws RpcException;
}
