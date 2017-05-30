package com.git.wuqf.rpc;

import java.util.Map;

/**
 * Created by wuqf on 17-3-11.
 */
public interface Result {

    Object getValue();

    Throwable getException();


    /**
     * Recreate.
     * <p>
     * <code>
     * if (hasException()) {
     * throw getException();
     * } else {
     * return getValue();
     * }
     * </code>
     *
     * @return result.
     */
    Object recreate() throws Throwable;

    boolean hasException();

    Map<String, String> getAttachments();

    String getAttachment(String key);

    String getAttachment(String key, String defaultValue);
}
