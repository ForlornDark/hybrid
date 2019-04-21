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
                            SocketChannel channel = (SocketChannel) next.channel();
                            log.info("read:"+next.attachment());
                            if (channel != null ){
                                if (channel.isConnected() && channel.isOpen()){
                                    int read = 0;
                                   try{
                                      read = channel.read(buffer);
                                      if (read > 0){
                                          String str = new String(buffer.array(),0,read);
                                          buffer.clear();
                                          log.info("rec:"+str);
                                          send(str);
                                      }else if (read == 0){

                                      }else {
                                          channel.close();
                                          next.cancel();
                                      }

                                    }catch (Exception e){
                                        log.info("连接关闭");
                                       channel.close();
                                        next.cancel();
                                    }

                                }else {
                                    next.cancel();
                                    channel.finishConnect();
                                }
                            }
                        }else if (next.isAcceptable()){
                            accept = serverSocketChannel.accept();
                            accept.configureBlocking(false);
                            log.info("accept"+accept.getRemoteAddress().toString());
                            accept.register(selector,SelectionKey.OP_READ,"fuck");
                            list.add(accept);
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
        buffer.flip();
        Iterator<SocketChannel> iterator = list.iterator();
        while (iterator.hasNext()){
            SocketChannel next = iterator.next();
            if (next.isConnected()){
                try {
                    next.write(buffer);
                } catch (IOException e) {
                    iterator.remove();
                    try {
                        next.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }else {
                try {
                    next.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                iterator.remove();
            }

        }
    }
}
