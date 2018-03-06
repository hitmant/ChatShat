//package server;
import java.net.*;
import java.io.*;


public class Server {
	public static void main(String[] a){
		ServerSocket ssoc = null;
		Socket csoc = null;
		InputStream is = null;
		BufferedReader br = null;
		OutputStream os = null;
		Writer out = null;
		String inputline, outputline = "kys\r\n";
		
		System.out.println("init done");
		
		try {
			ssoc = new ServerSocket(7777);
			csoc = ssoc.accept();
			is = csoc.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			os = csoc.getOutputStream();
			out = new OutputStreamWriter(os, "UTF-8");
			System.out.println("connection and streams done");
		} catch (IOException e){
			System.out.println(e.getStackTrace());
		}
		
		
		try{
			
			while ((inputline = br.readLine()) != null){
				System.out.println(inputline + "||" + outputline);
				if (inputline.equals("quit\r\n")){
					csoc.close();
					break;
				}
				//Thread.sleep(1000);
				out.write(outputline);
				out.flush();
				System.out.println("written");
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception d) {}
	}

}
