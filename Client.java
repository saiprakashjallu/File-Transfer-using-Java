/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetransfer;
import java.net.*;
import java.io.*;

public class Client {
    
    public String ip;
    public String fileName;
    public String filePath;
    
    public String popUp;
    
    public void Client_1() {
        
        try{
            int filesize=1147483648;
		int bytesRead; 
		int currentTot = 0; 
		Socket socket = new Socket(ip,15123);
                
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                fileName = dis.readUTF();
                
                
                
		byte [] bytearray = new byte [filesize];
		InputStream is = socket.getInputStream(); 
		FileOutputStream fos = new FileOutputStream(filePath + "/" + fileName); 
		BufferedOutputStream bos = new BufferedOutputStream(fos); 
		bytesRead = is.read(bytearray,0,bytearray.length); 
		currentTot = bytesRead; 
		do { 
			bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot));
			if(bytesRead >= 0)
				currentTot += bytesRead; 
		} while(bytesRead > -1); 
		
		bos.write(bytearray, 0 , currentTot);
		bos.flush(); 
		bos.close(); 
		socket.close(); 
                
                popUp = "File Received Successfully.";
        }catch(Exception e){
            System.out.println(e);
            popUp = "Unable to Receive File. Please try again.";
        }
        
                
                
    }
    
    
}
