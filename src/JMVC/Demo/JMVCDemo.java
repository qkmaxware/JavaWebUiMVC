package JMVC.Demo;


import JMVC.Controller.Actions.Json;
import JMVC.Controller.Controller;
import JMVC.Server.MapRoute;
import JMVC.Server.WebServer;
import JMVC.Server.WebServerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chalseth
 */
public class JMVCDemo {

    public static void main(String[] args){

        //Create webserver
        WebServer server = new WebServer(80);
        
        //Create and register controller
        JMVCDemoController HomeController = new JMVCDemoController();
        server.RegisterController("Home", HomeController);
        
        //Run the server
        try {
            server.Start(() -> {
                server.TryOpenBrowser();
            });
        } catch (WebServerException ex) {
            Logger.getLogger(JMVCDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
