package com.lfm.test.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {

    private static Logger log = Logger.getLogger(Client.class.getSimpleName());
    static boolean run = true;

    public static void main(String[] args){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(32);
            SocketChannel channel = SocketChannel.open(new InetSocketAddress(9090));
            channel.configureBlocking(false);
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ);
            new Thread(new MyRunnable(selector)).start();
            int i = 0;
            String s = "";
            while (run){
                i++;
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextLine();
                run = !s.equals("exit");
                buffer.clear();
                buffer.put(s.getBytes());
                try {
                    buffer.flip();
                    int write = channel.write(buffer);
                    log.info("write:"+write);
                } catch (IOException e) {
                    channel.close();
                    run = false;
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyRunnable implements Runnable{
        private Selector selector;
        private ByteBuffer buffer = ByteBuffer.allocate(32);

        public MyRunnable(Selector selector){
            this.selector = selector;
        }
        @Override
        public void run() {
            while (run){
                try {
                    int select = selector.select();
                    if (select <  1){
                        log.info("..........");
                        continue;
                    }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if (next.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) next.channel();
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        if (read > 0){
                            log.info("rec:"+new String(buffer.array(),0,read));
                        }else if (read ==0){
                            buffer.clear();
                        }else {
                            run = false;
                        }

                    }
                    iterator.remove();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
