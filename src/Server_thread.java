import java.net.*;
import java.io.*;

public class Server_thread implements Runnable{
	
	private Socket soc;
	
	public Server_thread(Socket soc){
		this.soc = soc;
	}
	
	public void run(){
		System.out.println("thread started");
		
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
