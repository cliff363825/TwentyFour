package com.onevgo.j2se.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 * <p>
 * 二、通道的主要实现类
 * java.nio.channels.Channel 接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 本地 IO：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * <p>
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * <p>
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 * <p>
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * <p>
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 * <p>
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 */
@Slf4j
public class ChannelMain {
    public static void main(String[] args) {
//        log.info("{}", System.getProperty("user.dir"));
//        testChannel();
//        testChannelMap();
//        testChannelTransfer();
//        testScatterAndGather();
        testCharset();
    }

    private static void testChannel() {
        //利用通道完成文件的复制（非直接缓冲区）
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel outChannel = null;
        FileChannel inChannel = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");

            //1. 获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //2. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //3. 将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                buf.flip(); //切换读取数据的模式

                //4. 将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear(); //清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //使用直接缓冲区完成文件的复制(内存映射文件)
    private static void testChannelMap() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

            //内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            //直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //通道之间的数据传输(直接缓冲区) ----简单易行 推荐！
    private static void testChannelTransfer() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

//            inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //分散和聚集
    private static void testScatterAndGather() {
        RandomAccessFile inRaf = null;
        FileChannel inChannel = null;
        RandomAccessFile outRaf = null;
        FileChannel outChannel = null;
        try {
            inRaf = new RandomAccessFile("1.txt", "rw");

            //1. 获取管道
            inChannel = inRaf.getChannel();

            //2. 分配指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(100);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);

            //3. 分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            inChannel.read(bufs);

            for (ByteBuffer buf : bufs) {
                buf.flip(); // 写转读
            }

            log.info("{}", new String(buf1.array(), 0, buf1.limit()));
            log.info("{}", new String(buf2.array(), 0, buf2.limit()));

            //4. 聚集写入
            outRaf = new RandomAccessFile("2.txt", "rw");
            outChannel = outRaf.getChannel();
            outChannel.write(bufs);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inRaf != null) {
                try {
                    inRaf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outRaf != null) {
                try {
                    outRaf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //字符集
    private static void testCharset() {
        Charset charset = Charset.forName("UTF-8");

        //获取编码器
        CharsetEncoder ce = charset.newEncoder();

        //获取解码器
        CharsetDecoder cd = charset.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("尚硅谷");
        cBuf.flip(); // 写转读

        //编码
        try {
            ByteBuffer bBuf = ce.encode(cBuf);
            log.info("{}", bBuf);
            log.info("{}", Arrays.toString(bBuf.array()));

            CharBuffer cBuf2 = cd.decode(bBuf);
            log.info("{}", bBuf);
            log.info("{}", cBuf2.toString());

            bBuf.rewind();
            Charset cs2 = Charset.forName("GBK");
            CharBuffer cBuf3 = cs2.decode(bBuf);
            log.info("{}", cBuf3.toString());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

    }
}
