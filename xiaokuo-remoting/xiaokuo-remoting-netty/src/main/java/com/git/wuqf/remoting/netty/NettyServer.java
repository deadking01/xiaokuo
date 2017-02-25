package com.git.wuqf.remoting.netty;

import com.git.wuqf.remoting.Channel;
import com.git.wuqf.remoting.ChannelHandler;
import com.git.wuqf.remoting.Server;
import com.git.wuqf.remoting.transport.AbstractServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by wuqf on 17-2-25.
 */
public class NettyServer extends AbstractServer implements Server {

    private Map<String, Channel> channels;

    private ServerBootstrap bootstrap;


    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyServer(URL url, ChannelHandler channelHandler) {
        super(url, channelHandler);
    }

    public void doOpen() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        bootstrap = new ServerBootstrap();
        NettyHandler nettyHandler = new NettyHandler(getUrl(), this);

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(nettyHandler);
                    }
                });

        ChannelFuture f = bootstrap.bind(getBindAddress());


    }

    public void doClose() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public Collection<Channel> getChannels() {
        Collection<Channel> chs = new HashSet<>();
        for (Channel channel : channels.values()) {
            chs.add(channel);
        }
        return chs;
    }
}
