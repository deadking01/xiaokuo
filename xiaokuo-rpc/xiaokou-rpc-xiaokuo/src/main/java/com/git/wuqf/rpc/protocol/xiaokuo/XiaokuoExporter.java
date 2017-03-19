package com.git.wuqf.rpc.protocol.xiaokuo;

import com.git.wuqf.rpc.Exporter;
import com.git.wuqf.rpc.Invoker;
import com.git.wuqf.rpc.protocol.AbstractExporter;

import java.util.Map;

/**
 * Created by wuqf on 17-3-11.
 */
public class XiaokuoExporter<T> extends AbstractExporter{
    private String key;
    private Map<String,Exporter<?>> exporters;

    public XiaokuoExporter(Invoker invoker, String key, Map<String, Exporter<?>> exporters) {
        super(invoker);
        this.key = key;
        this.exporters = exporters;
    }

    @Override
    public void unexport() {
        super.unexport();
        exporters.remove(key);
    }

}
