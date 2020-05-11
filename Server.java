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
public class Server {
    
    //ServerSocket serverSocket = null;
    //Socket clientSocket = null;
    
    public static void main(String ... args){
    
        try{
            
            ServerSocket serverSocket = new ServerSocket(9000);
            boolean isStop = false;
           
            while(!isStop)
            {
            //accept waits for client requests and return a socket for connection
                Socket clientSocket = serverSocket.accept();
                
                //Server can respond to multiple clients concurrently ,ie a Multithreaded server
                //The accept will create a new socket object
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
                
            }        
    }
        catch(IOException i)
        {
            System.out.println("Port 9000 is already in use");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
            
    
    
    
}
