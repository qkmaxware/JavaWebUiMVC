/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server;

import JMVC.Controller.Actions.Action;
import JMVC.Controller.Controller;
import JMVC.Server.Request.Request;
import java.awt.Desktop;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author chalseth
 */
public class WebServer {
    
    private HashMap<String,Controller> controllers = new HashMap<String,Controller>();
    private int port;
    
    public WebServer(int port){
        this.port = port;
    }
    
    public void RegisterController(String name, Controller ctrl){
        this.controllers.put(name, ctrl);
    }
    
    public void DeleteController(String name){
        this.controllers.remove(name);
    }
    
    public void ClearControllers(){
        this.controllers.clear();
    }
    
    public Action InvokeRequest(Request req){
        HashMap<String,String> mapping = MapRoute.Parse(req.resource);
        
        Controller ctrl = controllers.get(mapping.get("controller"));
        String action = mapping.get("action");
        
        if(ctrl != null){
            try{
                Action a = ctrl.Invoke(req, action);
                return a;
            }
            catch(InvocationTargetException ex){
                ServerLogger.Log(Level.WARNING, ex.getCause());
            }
            catch(Exception e){
                ServerLogger.Log(Level.WARNING, e);
            }
        }
        return null;
    }
    
    public void Start(Runnable ... afterServerStartsEvents)throws WebServerException{
        try{
            //Init server
            ServerSocket server = new ServerSocket(port);
            ServerLogger.Log("Server Started On - "+ server.getInetAddress().toString());
            
            for(Runnable run : afterServerStartsEvents){
                if(run != null)
                    run.run();
            }
            
            while(true){
                Socket client = server.accept();
                ServerClient handler = new ServerClient(this, client);
            }
            
        }catch(Exception e){
            ServerLogger.Log(Level.SEVERE, "Web server has experienced a fatal error");
            throw new WebServerException("Web server fatal error", e);
        }
    }
    
    public void TryOpenBrowser(){
        try{
            if(Desktop.isDesktopSupported()){
                Desktop.getDesktop().browse(new URI("http://localhost:"+port));
            }else{
                throw new Exception("Desktop is not supported");
            }
        }catch(Exception e){}
    }
}
