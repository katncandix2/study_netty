package Virtualasynchronous;

import BIO.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String []args){
        int port = 8080;
        if(args !=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);

            }catch (NumberFormatException e){
                //todo
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:"+port);
            Socket socket = null;
            // 创建I/O 任务线程池
            TimeServerHandlerExecutePool singleExecuter = new TimeServerHandlerExecutePool(50,10000);
            while (true){
                socket = server.accept();
                singleExecuter.execute(new TimeServerHandler(socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server!=null){
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server=null;
            }
        }


    }

}
