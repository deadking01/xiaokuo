package com.git.wuqf.rpc;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wuqf on 17-3-11.
 */
public class RpcResult implements Result,Serializable{


    private Object result;
    private Map<String,String> attachments;
    private Throwable exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public Object recreate() {
        return exception==null?result:exception;
    }

    @Override
    public boolean hasException() {
        return exception!=null;
    }

    @Override
    public String getAttachment(String key) {
        return attachments.get(key);
    }

    @Override
    public String getAttachment(String key, String defaultValue) {
        return attachments.get(key);
    }
}
