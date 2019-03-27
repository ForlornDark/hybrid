package com.lfm.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


public class Server {
    private static Logger log = Logger.getLogger(Client.class.getSimpleName());
    private static List<SocketChannel> list = new ArrayList<>();
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(32);

        try {
            Selector selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9090));
            //非阻塞
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                int select = selector.select();
                log.info(".....");
                SocketChannel accept= null;
                if (select > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        if (next.isReadable()){
                            log.info("read");
                            SocketChannel channel = (SocketChannel) next.channel();
                            int read = channel.read(buffer);
                            if (read > 0){
                                buffer.flip();
                                String str = new String(buffer.array());
                                log.info("rec:"+read);
                                send(str);
                            }
                        }else if (next.isAcceptable()){
                            accept = serverSocketChannel.accept();
                            accept.configureBlocking(false);
                            list.add(accept);
                            log.info("accept"+accept.getRemoteAddress().toString());
                            accept.register(selector,SelectionKey.OP_READ);
                        }else if (next.isConnectable()){
                            log.info("connect");
                        }else if (next.isWritable()){
                            log.info("write");
                        }
                        iterator.remove();
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(String string){
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put(string.getBytes());
        Iterator<SocketChannel> iterator = list.iterator();
        while (iterator.hasNext()){
            SocketChannel next = iterator.next();
            if (next.isConnected()){
                try {
                    next.write(buffer);
                } catch (IOException e) {
                    iterator.remove();
                    try {
                        next.finishConnect();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }else {
                try {
                    next.finishConnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                iterator.remove();
            }

        }
    }
}
