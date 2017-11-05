import java.net.*;
import java.io.*;

public class Printer {
	
	public static void main(String args[]) {
		try {
			while(true) {
				ServerSocket servSock = new ServerSocket(7000);
				Socket sock = servSock.accept();
				DataInputStream dis = new DataInputStream(sock.getInputStream());
				int timestamp = dis.readInt();
				for(int i=timestamp; i<timestamp+10; i++) {
					System.out.println(i);
					Thread.sleep(500);
				}
				sock.close();
				servSock.close();
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
