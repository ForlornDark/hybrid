package com.lfm.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class Client {

    private static int port =9091;

    public static void main(String[] args){
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = null; // (5)
            try {
                f = b.connect("localhost", port).sync();
                // Wait until the connection is closed.
                Scanner scanner = new Scanner(System.in);
                boolean run = true;
                while (run){
                    ByteBuf buffer = f.channel().alloc().buffer(32);
                    String body = scanner.nextLine();
                    buffer.writeBytes(body.getBytes());
                    run = !"exit".equals(body);
                    f.channel().writeAndFlush(buffer).sync();
                }
                f.channel().closeFuture();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
