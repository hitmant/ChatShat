import java.io.*;
import java.net.*;

public class Hello{

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

  write.write("DEFINE fd-eng-lat gold\r\n");
  write.flush();
  System.out.println("Define written");
  
  
  String l = in.readLine();
  System.out.println(l);
  System.out.println("ex loop");
  
  write.write("quit\r\n");
  write.flush();
  
  if (soc != null) {
   try {
    soc.close();
   } catch (IOException e){}
  }
  
 }

}
