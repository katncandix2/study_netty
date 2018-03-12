package NIO;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class FileChannel {


    public static void  main(String []args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\1.txt","rw");

            java.nio.channels.FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);

            while (bytesRead !=-1){
                System.out.println("Read" + bytesRead);
                buf.flip();


                while (buf.hasRemaining()){
                    System.out.print((char)buf.get());
                    System.out.println("---------------------");
                    System.out.print(buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }

            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
