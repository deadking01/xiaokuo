package com.git.wuqf.xiaokuo.rpc;

import java.util.Map;

/**
 * Created by wuqf on 17-3-11.
 */
public interface Invocation {
    String getMethodName();

    Class<?>[] getParameterTypes();

    Object[] getArguments();

    Map<String, String> getAttachments();

    String getAttachment(String key);

    String getAttachment(String key, String defaultValue);

    Invoker<?> getInvoker();
}
