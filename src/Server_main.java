//package server;
import java.net.*;
import java.io.*;
import java.util.*;


public class Server_main {
	public static void main(String[] a){
		ServerSocket ssoc = null;
		Socket csoc = null;
		//InputStream is = null;
		//BufferedReader br = null;
		OutputStream os = null;
		Writer out = null;
		//String inputline;
		
		System.out.println("init done");
		
		try {
			ssoc = new ServerSocket(7777);
			csoc = ssoc.accept();
			//is = csoc.getInputStream();
			//br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			os = csoc.getOutputStream();
			out = new OutputStreamWriter(os, "UTF-8");
			System.out.println("connection and streams done");
		} catch (IOException e){
			System.out.println(e.getStackTrace());
		}
		
		Server_thread input = new Server_thread(csoc);
		(new Thread(input)).start();
		
		Scanner scan = new Scanner(System.in);
		StringBuilder msg = new StringBuilder();
		
		try{
			
			while (!csoc.isClosed() && csoc.isConnected()){
				
				msg.delete(0, msg.length());
				msg.append(scan.nextLine());
				msg.append("\r\n");
				
				out.write(msg.toString());
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception d) {}
		
		scan.close();
	}

}
