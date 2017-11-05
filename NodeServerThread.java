import java.net.*;
import java.io.*;

public class NodeServerThread extends Thread {

	DataInputStream dis;
	DataOutputStream dos;
	ServerSocket servSock;
	Socket sock;

	public NodeServerThread(ServerSocket ss) throws IOException {
		super();
		servSock = ss;
	}

	public void run() {
		try {
			System.out.println("Aceitando conexoes na porta " + servSock.getLocalPort());
			sock = servSock.accept();
			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());

			while (true) {
				try {
					Thread.sleep(1000);
					System.out.println(dis.readUTF());
					Thread.sleep(1000);
					dos.writeUTF("\nmensagem de thread servidor " + servSock.getLocalPort());
				} catch (SocketException se) {
					break;
				}
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
