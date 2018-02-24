/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chalseth
 */
public class ServerLogger {
    private final static Logger log = Logger.getLogger(ServerLogger.class.getName());
    
    public static void SetLevel(Level level){
        log.setLevel(level);
    }
    
    public static void Log(Level level, Object msg){
        log.log(level, msg.toString());
    }
    
    public static void Log(Level level, Exception msg){
        log.log(level, msg.toString() + " - "+msg.getMessage());
    }
    
    public static void Log(Object msg){
        Log(Level.INFO, msg);
    }
    
    public static void Log(Exception msg){
        Log(Level.INFO, msg);
    }
}
