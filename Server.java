/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetransfer;
import java.net.*;
import java.io.*;


/**
 *
 * @author amitkumar
 */
public class Server {
    
    public String path;
    public String notif;
    
    public void Server_1() {
            Send ob = new Send();
            
            int i;
            
            if(path.lastIndexOf("/") == -1){
                 i = path.lastIndexOf("\\");
            }else{
                i = path.lastIndexOf("/");
            }
            
            //int i = path.lastIndexOf("/");
            
            String fileName = path.substring(i);
            
            
            
            
            try{
                ServerSocket serverSocket = new ServerSocket(15123);
		Socket socket = serverSocket.accept(); 
                
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(fileName);
                
                
                String s = "Accepted connection : " + socket;
                File transferFile = new File (path);
		
		byte [] bytearray = new byte [(int)transferFile.length()]; 
		
		FileInputStream fin = new FileInputStream(transferFile);
		BufferedInputStream bin = new BufferedInputStream(fin); 
		
		bin.read(bytearray,0,bytearray.length); 
		OutputStream os = socket.getOutputStream(); 
		
		System.out.println("Sending Files..."); 
		
		os.write(bytearray,0,bytearray.length); 
		
		os.flush(); 
		
		socket.close(); 
                notif = "File Sent Successfully.";
                System.out.println("File transfer complete");
            }catch(Exception e){
                notif = "Unable to Send File. Please try again.";
            }
                
    }
    
    
}
