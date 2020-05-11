/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readmetransfer;

import java.io.*;
import java.net.*;
//to validate ip address
//import org.apache.commons.validator.routines.InetAddressValidator;


/**
 *
 * @author akash antal
 */
public class Client {
    
    void Client_download()
    {
       try
       { 
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        
        String ipAddress = "";
        String fileName = "";
        
        boolean isValid = false;
        
        //while(!isValid)
        //{
            System.out.println("Please enter a valid IP Address");
            ipAddress = reader.readLine();
            //InetAddressValidator validator = new InetAddressValidator();
            //isValid = validator.isValid(ipAddress);
        //}    
            System.out.println("Enter a filename");
            fileName = reader.readLine();
            
            Socket socket = new Socket(ipAddress,9000);
            InputStream inputByte = socket.getInputStream();
            BufferedInputStream input = new BufferedInputStream(inputByte);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            
            out.println(fileName);
            int code = input.read();
            if(code == 1)
            {
                BufferedOutputStream outfile = new BufferedOutputStream(new FileOutputStream
                                                                         ("D:\\"+fileName ));
                byte [] buffer = new byte[1024];
                int bytesRead = 0;
                while((bytesRead = input.read(buffer)) !=-1)
                {
                    System.out.print(".");
                    outfile.write(buffer,0,bytesRead);
                    outfile.flush();
                    
                }
                System.out.println("File" + fileName +"successfully downloaded");
                
                
            }
            else
            {
                System.out.println("File not present");
            }
            
            
        
       }
       catch(Exception e)
       {
       }
       
    }
    
}
