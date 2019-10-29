package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

		while (true) {
			Socket socket = serverSocket.accept();
			ClientReader CR = new ClientReader(socket);
			ExecutorService pool = Executors.newFixedThreadPool(20);
			pool.execute(CR);
			pool.shutdown();
		}
	}

}


class ClientReader implements Runnable {
	private Socket sock;

	ClientReader(Socket socket){
		sock = socket;
	}

	public void run() {
		try {
			InputStream input = sock.getInputStream();
			OutputStream output = sock.getOutputStream();
			int byteRead;
			while((byteRead = input.read()) != -1){
				output.write(byteRead);
			}
			sock.shutdownOutput();
		} catch (IOException ioe){
			System.out.println("We caught an unexpected exception");
			System.err.println(ioe);
		}
	}
}
