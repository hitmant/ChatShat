import java.io.*;
import java.net.*;

public class Client_thread implements Runnable{
	private Socket soc;
	
	public Client_thread(Socket soc){
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
				System.out.println(inputline);
			} catch (IOException e) {
				break;
			}
		}
		Client_main.setThread(false);
		
	}
}
