import java.net.*;
import java.io.*;

public class Server_thread implements Runnable{
	
	private Socket soc;
	
	public Server_thread(Socket soc){
		this.soc = soc;
	}
	
	public void run(){
		System.out.println("thread started");
		
		StringBuilder inputline = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
										soc.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			return;
		}
		
		while (!inputline.toString().equals("null")
				&& !inputline.toString().equals("quit")){
			try {
				inputline.delete(0, inputline.length());
				inputline.append(in.readLine());
				System.out.println("\n" + inputline);
			} catch (IOException e) {
				break;
			}
		}
		
		Server_main.setThread(false);
	}

}
