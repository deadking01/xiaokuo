package com.git.wuqf.remoting;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * Created by wuqf on 17-2-24.
 */
public interface Server extends EndPoint, Resetable {

    boolean isBound();

    Collection<Channel> getChannels();

    Channel getChannel(InetSocketAddress remoteAddress);

}
