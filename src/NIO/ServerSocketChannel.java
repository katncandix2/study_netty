package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannel {
    public static void main(String []args) throws IOException {
        //1.监听客户端连接
        ServerSocketChannel acceptorSvr = ServerSocketChannel.open();

        //2.绑定监听端口
        acceptorSvr.socket().bind(
                new InetSocketAddress(InteAddress.getByName("IP"),port)
        );

        acceptorSvr.configureBlocking(false);


        //3.创建Reactor 线程,创建多路复用器 并启动线程

        Selector selector = Selector.open();

        new Thread(new ReactorTask()).start();


        //4.将ServerSocketChannel 注册到Reactor 线程的多路复用器 Selector 上 监听
        // ACCEPT 事件 ,示例代码如下
        SelectionKey key = acceptorSvr.register(selector,SelectionKey.OP_ACCEPT,ioHandler);

        //5.多路复用器在线程run 方法的无限循环体内轮训准备就绪的Key
        int num = selector.select();
        Set selectedKeys = selector.selectedKeys();
        Iterator it = selectedKeys.iterator();
        while (it.hasNext()){
            SelectionKey key = (SelectionKey)it.next();

            //deal with I/O event ...
        }


        //6.多路复用器监听到有新的客户端接入,处理新的接入请求,完成TCP 三次握手
        //建立物理链路
        SocketChannel channel = svrChannel.accept();

        //7.设置客户端链路为非阻塞式
        channel.configureBlocking(false);
        channel.socket().setReuseAddress(true);

        //8.将新接入的客户端连接注册到Reactor 线程的多路复用器上
        //监听读操作 读取客户端发送的网络信息
        SelectionKey key1 = socketChannel.register(selector,SelectionKey.OP_READ,
                ioHandler);

        //9.异步读取客户端请求到缓冲区
        int readNumber = channel.read(receivedBuffer);

        //10.对ByteBuffer 进行编解码
        Object message = null;
        while (buffer.hasRemain()){
            byteBuffer.mark();
            Object message = decode(byteBuffer);
            if(message == null){
                byteBuffer.reset();
                break;
            }
            messageList.add(message);
        }

        //关闭流
        if(!byteBuffer.hasRemain()){
            byteBuffer.clear();
        }else {
            byteBuffer.compact();
            if(messageList !=null && !messageList.isEmpty()){
                for (Object messageM :messageList){
                    handkerTask(messageM);
                }
            }

        }

    }
}
