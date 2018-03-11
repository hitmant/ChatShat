//package server;
import java.net.*;
import java.io.*;
import java.util.*;


public class Server_main {
	
	private static boolean thread = false;
	
	public static void setThread(boolean b){
		thread = b;
	}
	
	public static void main(String[] a){
		String ip;
		int port = 7777;
		ServerSocket ssoc = null;
		Socket soc = null;
		OutputStream os = null;
		Writer out = null;
		Scanner scan = new Scanner(System.in);
		StringBuilder msg = new StringBuilder();
		
		System.out.print("Enter the IP address of your friend: ");
		ip = scan.nextLine();
		
		System.out.println("init done");
		
		try {
			soc = new Socket(ip, port);
			os = soc.getOutputStream();
			out = new OutputStreamWriter(os, "UTF-8");
			System.out.println("connection and streams done");
		} catch (IOException e){
			try {
				ssoc = new ServerSocket(7777);
				soc = ssoc.accept();
				os = soc.getOutputStream();
				out = new OutputStreamWriter(os, "UTF-8");
				System.out.println("connection and streams done");
			} catch (IOException f){
			}
		}
		
		Server_thread input = new Server_thread(soc);
		Thread inputT = new Thread(input);
		inputT.start();
		thread = true;
		
		
		try{
			
			while (thread && !msg.toString().equals("quit\r\n")){
				
				msg.delete(0, msg.length());
				msg.append(scan.nextLine());
				msg.append("\r\n");
				
				out.write(msg.toString());
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception d) {}
		
		inputT.interrupt();
		scan.close();
	}

}
