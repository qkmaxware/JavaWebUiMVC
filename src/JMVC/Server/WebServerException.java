/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server;

/**
 *
 * @author chalseth
 */
public class WebServerException extends Exception{
    
    private Exception inner;
    
    public WebServerException(String msg, Exception inner){
        super(msg);
        this.inner = inner;
    }
    
    public Exception GetInnerException(){
        return inner;
    }
    
}
