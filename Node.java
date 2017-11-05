import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Node {

	ArrayList<NodeServerThread> serverThreads;
	ArrayList<NodeClientThread> clientThreads;
	final int port = 7000;
	final String network = "192.168.0.";
	String ip;
	int nodeCount;

	public Node(int nodeCount, String ip) throws IOException {
		this.ip = ip;
		this.nodeCount = nodeCount;
		serverThreads = new ArrayList<>();
		clientThreads = new ArrayList<>();
		int this_host = Integer.parseInt(ip.split("\\.")[3]);
		for (int i = 1; i <= nodeCount; i++) {
			if (i != this_host) {
				ServerSocket ss = new ServerSocket(port + i);
				NodeServerThread nst = new NodeServerThread(ss);
				serverThreads.add(nst);
				nst.start();
			}
		}
	}

	public void connect(String ip, int port) {
		NodeClientThread nct = new NodeClientThread(ip, port);
		clientThreads.add(nct);
		nct.start();
	}
	
	public void print() {
		try {
			Socket sock = new Socket("192.168.0.254", port);
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			dos.writeInt(10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			int nodeCount = Integer.parseInt(args[0]);
			Node node = new Node(nodeCount, args[1]);
			int this_host = Integer.parseInt(node.ip.split("\\.")[3]);
			for (int i = 1; i <= nodeCount; i++) {
				if (i != this_host) {
		 			node.connect(node.network+i, node.port+this_host);
				}
			}
			node.print();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
