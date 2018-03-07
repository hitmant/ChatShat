import java.io.*;
import java.net.*;

public class Client_thread implements Runnable{
	private Socket soc;
	
	public Client_thread(Socket soc){
		this.soc = soc;
	}
	
	public void run(){
		System.out.println("thread started");
		
		StringBuilder input = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
													soc.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			return;
		}
		
		while (true){
			try {
				System.out.println(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
