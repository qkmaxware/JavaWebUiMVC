/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Controller.Actions.Http;

import java.util.HashMap;

/**
 *
 * @author halse
 */
public class HttpResponse {
    
    private String httpVersion = "1.0";
    private int code;
    private String name;
    
    private HashMap<String,String> headers = new HashMap<String,String>();
    
    private String contentType;
    private String body;
    
    public void SetHttpStatus(HttpStatusCode code){
        this.code = code.GetStatusCode();
        this.name = code.GetName();
    }
    
    public void SetHeader(String name, String value){
        headers.put(name, value);
    }
    
    public void SetContentType(Mime mime){
        SetContentType(mime.toString());
    }
    
    public void SetContentType(String type){
        this.contentType = type;
    }
    
    public void SetBody(String content){
        this.body = content;
    }
    
    public String GetResponse(){
        StringBuilder builder = new StringBuilder();
        String s =
                "HTTP/" + String.valueOf(httpVersion) + " " + code + " " + String.valueOf(name) + "\r\n"+
                "Content-type: " + String.valueOf(contentType) + "\r\n";
        builder.append(s);
        
        headers.forEach((key, value) -> {
            builder.append(String.valueOf(key));
            builder.append(": ");
            builder.append(String.valueOf(value));
            builder.append("\r\n");
        });
        
        builder.append("\r\n");
        builder.append(String.valueOf(body));
        return builder.toString();
    }
    
    public String toString(){
        return GetResponse();
    }
}
