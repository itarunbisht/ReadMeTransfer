/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readmetransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


class Authenticate {
    
    private volatile static Authenticate single_instance=null;
    public Authenticate(){};
    public static Authenticate getInstance(){
        if(single_instance==null){
            synchronized(Authenticate.class){
                if(single_instance==null){
                    single_instance=new Authenticate();
                }
            }
        }
        return single_instance;
    }
    public Connection getConnection(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Users","tarun","12345");
            return con;
        }catch(Exception E){
            System.out.println(E);
        }
        return null;
    }
    
     public void login(String user , String pass){
       
        try{
            Connection con=getConnection();
            PreparedStatement preparedStatement=con.prepareStatement("Select * from Tarun.AUTHENTICATION");
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                if(rs.getString("username").equals(user) && rs.getString("password").equals(pass)){
                    System.out.println("Login successfully");
                    Client c = new Client();
                    c.Client_download();
                }
               
            }
        }catch(Exception e){System.out.println(e);}
    }
    
    
   public void register(String user , String pass){
       
       try{
            Connection con=getConnection();
            PreparedStatement preparedStatement=con.prepareStatement("Insert into TARUN.AUTHENTICATION values(?,?)");
            preparedStatement.setString(1,user);
            preparedStatement.setString(2, pass);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
