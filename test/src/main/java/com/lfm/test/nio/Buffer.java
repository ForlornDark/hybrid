package com.lfm.test.nio;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class Buffer {

    static Logger log = Logger.getLogger(Buffer.class.getSimpleName());

    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("123".getBytes());
        log.info(String.valueOf(buffer.position()));
        log.info(new String(buffer.array(),0,buffer.position()));
        buffer.flip();
        log.info(String.valueOf(buffer.position()));
    }
}
