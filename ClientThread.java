/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readmetransfer;

import java.io.*;
import java.net.*;
/**
 *
 * @author akash antal
 */
public class ClientThread extends Thread {
    
    Socket socket;
    BufferedReader reader;  //  To read the file requested by client
    BufferedOutputStream out;      // To transer the file to client
    BufferedInputStream fileReader;
    
    
    ClientThread(Socket socket)
    {
        this.socket=socket;
    }
    
    public void run()
    {
        try
        {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out = new BufferedOutputStream(socket.getOutputStream());
            
            //System.out.println("Enter the fie");
            String fileName = reader.readLine();
            System.out.println("File Name is " + fileName + "has requested by" + 
                    socket.getInetAddress().getHostAddress());
            
            File file = new File(fileName);
            if(!file.exists())
            {
                byte code = (byte)0;
                out.write(code);
                closeConnection();
            }
            else
            {
                //bytes can only represent -127 to 127 
                byte code = (byte)1;
                out.write(code);
                
                fileReader = new BufferedInputStream(new FileInputStream(file));
                
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while((bytesRead = fileReader.read(buffer)) != -1 )
                {
                   out.write(buffer, 0, bytesRead);
                   out.flush();
                   closeConnection();
                }    
            }    
            
        }catch(Exception e)
        {   
            System.out.println(e.toString());    
        }
    }
    
    void closeConnection()
    {
        try
        {
            if(out!=null)
            {   
                out.close();
            }
            
            if(reader!=null)
            {   
                reader.close();
            }
            if(fileReader!=null)
            {
                fileReader.close();
            }
            if(out!=null)
            {   
                socket.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
