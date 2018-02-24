/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server;

import JMVC.Controller.Actions.Action;
import JMVC.Server.Request.Request;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author chalseth
 */
public class ServerClient extends Thread{
    
    private Socket s;
    private WebServer spawner;
    
    public ServerClient (WebServer spawner, Socket s){
        this.s = s;
        this.spawner = spawner;
        start();
    }
    
    public void run(){
        try{
            //Initialize IO
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new PrintWriter(new PrintStream(new BufferedOutputStream(s.getOutputStream()))));
            
            //Read request
            String sin = in.readLine();
            Request req = new Request(sin);
            ServerLogger.Log("Recieved Request - " + sin);
            
            if(!req.isValid()){
                throw new Exception("The request is in an invalid format");
            }

            //Invoke 
            Action result = spawner.InvokeRequest(req);
            if(result != null){
                out.write(result.GetMessage());
                out.flush();
            }
            
            out.close();
            in.close();
            
            ServerLogger.Log("Recieved Resolved - " + sin);
        }catch(Exception e){
            e.printStackTrace();
            ServerLogger.Log(Level.WARNING, "Client processing error - "+e.toString());
        }
        finally{
            try{
                s.close();
            }catch(Exception e){
                ServerLogger.Log(Level.WARNING, "Client flush error - "+e.toString());
            }
        }
    }
}
