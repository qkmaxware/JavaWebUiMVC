/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server.Request;

import JMVC.Server.ServerLogger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chalseth
 */
public class Request {
    
    private static Pattern pattern = Pattern.compile("^(?<type>\\w*)\\s*(?<resource>[^\\s]*)\\s*(?<format>.*)$");
    
    public final RequestType type;
    public final String resource;
    public final String format;
    public final HashMap<String,String> parameters = new HashMap<String,String>();
    
    public Request(String req){
        req = req.trim();
        Matcher m = pattern.matcher(req);
        String resource = null;
        String format = null;
        RequestType type = RequestType.NONE;
        if(m.find()){
            resource = m.group("resource");
            format = m.group("format");
            
            String[] params = resource.split("\\?");
            resource = params[0];
            if(params.length > 1){
                String[] keyvaluepairs = params[1].split("\\&");
                for(String pair : keyvaluepairs){
                    String[] parts = pair.split("\\=");
                    String key = parts[0];
                    String value = parts[1];
                    try{
                        parameters.put(java.net.URLDecoder.decode(key, "UTF-8"), java.net.URLDecoder.decode(value, "UTF-8"));
                    }
                    catch(Exception e){
                        ServerLogger.Log("Bad formatting for query parameter: "+key);
                    }
                }
            }
            
            String stype = m.group("type").toLowerCase();
            switch(stype){
                case "get":
                    type = RequestType.GET; break;
                case "post":
                    type = RequestType.POST; break;
                case "head":
                    type = RequestType.HEAD; break;
                case "put":
                    type = RequestType.PUT; break;  
                case "delete":
                    type = RequestType.DELETE; break;
                case "options":
                    type = RequestType.OPTIONS; break;
                case "connect":
                    type = RequestType.CONNECT; break;
                default:
                    type = RequestType.NONE;
            }
        }
        this.resource = resource;
        this.type = type;
        this.format = format;
    }
    
    public boolean isValid(){
        return this.resource != null && this.format != null;
    }
    
}
