import java.io.*;
import java.net.*;
import java.util.*;

public class Client_main{

 public static void main(String[] a) throws IOException{
  Socket soc = null;
  BufferedReader in = null;
  InputStream is = null;
  OutputStream out = null;
  Writer write = null;
  System.out.println("init done");
  
  try {
   soc = new Socket("localhost", 7777);
   is = soc.getInputStream();
   in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
   out = soc.getOutputStream();
   write = new OutputStreamWriter(out, "UTF-8");
   System.out.println("connection established");
  } catch (IOException e){
   System.out.println(e.toString());
  }
  
  Client_thread recieve = new Client_thread(soc);
  (new Thread(recieve)).start();

  StringBuilder msg = new StringBuilder();
  Scanner scan = new Scanner(System.in);
  //String inputline = null;
  
  while (!msg.toString().equals("quit\r\n") && !soc.isClosed()) {
    msg.delete(0, msg.length());
    msg.append(scan.nextLine());
    msg.append("\r\n");
    write.write(msg.toString());
    write.flush();
    
    //System.out.print((inputline = in.readLine()) + "\n>");
    //if (inputline.equals("quit"))
    //      break;
  }
  
  scan.close();
  
  if (soc != null) {
   try {
    soc.close();
   } catch (IOException e){}
  }
  
 }

}
