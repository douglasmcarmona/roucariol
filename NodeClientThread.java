import java.net.*;
import java.io.*;

public class NodeClientThread extends Thread {

	String ip;
	int port;
	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;

	public NodeClientThread(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public void run() {
		boolean connected = false;
		while (!connected) {
			try {
				Thread.sleep(1000);
				System.out.print("\nTentando se conectar em " + ip + "... ");
				sock = new Socket(ip, port);
				dis = new DataInputStream(sock.getInputStream());
				dos = new DataOutputStream(sock.getOutputStream());
				connected = true;
				System.out.println("Sucesso");
			} catch (IOException | InterruptedException e) {
				System.out.println("Falhou");
				continue;
			}
		}
		while(true) {
			try {
				Thread.sleep(1000);
				dos.writeUTF("mensagem de thread cliente " + port);
				Thread.sleep(1000);
				System.out.println(dis.readUTF());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				if(e instanceof SocketException) break;
				e.printStackTrace();
			}
		}
	}
}
