package com.onevgo.j2se.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

public class PipeMain {
    public static void main(String[] args) throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();

        //2. 将缓冲区中的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes(StandardCharsets.UTF_8));
        buf.flip();
        sinkChannel.write(buf);

        buf.clear();

        //3. 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        sourceChannel.read(buf);
        buf.flip();
        System.out.println(StandardCharsets.UTF_8.decode(buf).toString());

        sourceChannel.close();
        sinkChannel.close();
    }
}
