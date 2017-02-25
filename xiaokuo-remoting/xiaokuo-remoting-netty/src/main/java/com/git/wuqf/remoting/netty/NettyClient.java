package com.git.wuqf.remoting.netty;

import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.transport.AbstractClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URL;

/**
 * Created by wuqf on 17-2-24.
 */
public class NettyClient extends AbstractClient {

    private Bootstrap bootstrap;
    private Channel channel;
    private URL url;
    private ChannelHandler channelHandler;
    private EventLoopGroup group;

    public NettyClient(URL url, ChannelHandler channelHandler) {
        super(url, channelHandler);
        this.url = url;
        this.channelHandler = channelHandler;
        doOpen();
    }

    protected void doOpen() {
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        NettyHandler nettyHandler = new NettyHandler(url, this);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();

                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        p.addLast(nettyHandler);
                    }
                });
    }

    @Override
    protected void doClose() {
        group.shutdownGracefully();
    }

    @Override
    protected void doConnect() {
        ChannelFuture f = bootstrap.connect(getUrl().getHost(), getUrl().getPort());
        channel = f.channel();
    }

    @Override
    protected void doDisConnect() {
        NettyChannel.removeChannelIfDisconnected(channel);
    }

    @Override
    protected com.git.wuqf.remoting.Channel getChannel() {
        return NettyChannel.getOrAddChannel(channel, getUrl(), this);
    }


}
