package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);

		// Call the class implementing runnable
		InputReader IR = new InputReader(socket);
		OutputWriter OW = new OutputWriter(socket);
		// Make a thread for each runnable
		Thread threadInput = new Thread(IR);
		Thread threadOutput = new Thread(OW);
		// Start each thread
		threadInput.start();
		threadOutput.start();
	}
}

class InputReader implements Runnable {
	private Socket inputSock;

	InputReader(Socket socket){
		inputSock = socket;
	}

	// Create outputStream from socket and then write to server as long
	// as standard input read is not -1
	public void run() {
		try {
			OutputStream output = inputSock.getOutputStream();
			int byteTyped;
			while ((byteTyped = System.in.read()) != -1) {
				output.write(byteTyped);
			}
			inputSock.shutdownOutput();
		} catch (IOException ioe){
			System.out.println("We caught an unexpected exception");
			System.err.println(ioe);
		}

	}
}

class OutputWriter implements Runnable {
	private Socket outputSock;

	OutputWriter(Socket socket){
		outputSock = socket;
	}

	// Create inputStream from socket and then write to standard output
	// as long as inputStream read is not -1
	public void run(){
		try {
			InputStream input = outputSock.getInputStream();
			int byteRead;
			while ((byteRead = input.read()) != -1) {
				System.out.write(byteRead);
			}

			System.out.flush();
			outputSock.close();

		} catch (IOException ioe){
			System.out.println("We caught an unexpected exception");
			System.err.println(ioe);
		}
	}
}