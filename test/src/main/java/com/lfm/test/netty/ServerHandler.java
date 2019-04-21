package com.lfm.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    public static List<ChannelHandlerContext> list = new ArrayList<>();;

    public ServerHandler(){

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf) msg; // (1)
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String s = new String(bytes);
        System.out.println("rec:"+s);
        ByteBuf buf1 = ctx.alloc().buffer(buf.readableBytes());
        buf1.writeBytes(bytes);
        send(buf1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        list.remove(ctx);
        ctx.close();
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("register:"+ctx.channel().remoteAddress().toString());
        list.add(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("unregister:"+ctx.channel().remoteAddress().toString());
        list.remove(ctx);
    }

    private void send(ByteBuf body){
        Iterator<ChannelHandlerContext> iterator = list.iterator();
        while (iterator.hasNext()){
            ChannelHandlerContext next = iterator.next();
            body.retain();
            ChannelFuture channelFuture = next.writeAndFlush(body);
//            channelFuture.addListener(future -> {
//                assert channelFuture == future;
//                next.close();
//            }); // (4)
        }
    }
}
