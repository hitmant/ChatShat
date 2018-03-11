import java.io.*;
import java.net.*;
import java.util.*;

public class Client_main{
	private static boolean thread = false;

	public static void setThread(boolean b){
		thread = b;
	}

	public static void main(String[] a) throws IOException{
		Socket soc = null;
		OutputStream out = null;
		Writer write = null;
		System.out.println("init done");

		try {
			soc = new Socket("localhost", 7777);
			out = soc.getOutputStream();
			write = new OutputStreamWriter(out, "UTF-8");
			System.out.println("connection established");
		} catch (IOException e){
			System.out.println(e.toString());
		}

		Client_thread input = new Client_thread(soc);
		Thread inputT = new Thread(input);
		inputT.start();
		thread = true;

		StringBuilder msg = new StringBuilder();
		Scanner scan = new Scanner(System.in);

		while (thread && !msg.toString().equals("quit\r\n")) {
			msg.delete(0, msg.length());
			msg.append(scan.nextLine());
			msg.append("\r\n");
			write.write(msg.toString());
			write.flush();

		}

		inputT.interrupt();
		scan.close();

		if (soc != null) {
			try {
				soc.close();
			} catch (IOException e){}
		}

	}

}
