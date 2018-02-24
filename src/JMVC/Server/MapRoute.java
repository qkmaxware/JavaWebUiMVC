/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server;

import java.util.HashMap;

/**
 *
 * @author chalseth
 */
public class MapRoute {

        private static String[] format = new String[]{"controller", "action"};
	public static final HashMap<String,String> defaults = new HashMap<String,String>();

	static{
            defaults.put("controller", "Home");
            defaults.put("action", "Index");
	}

        public static String Build(String controller, String action){
            return controller+"/"+action;
        }
        
        public static HashMap<String,String> Parse(String resource){
            //Remove starting "/"
            if(resource.startsWith("/")){
                resource = resource.substring(1);
            }
            
            String[] parts = resource.split("/");
            HashMap<String,String> map = new HashMap<String,String>();
            
            for(int i = 0; i < format.length; i++){
                String value;
                
                if(i < parts.length){
                    String s = parts[i].trim();
                    if(s.isEmpty()){
                        value = defaults.containsKey(format[i]) ? defaults.get(format[i]) : "";
                    }else{
                        value = s;
                    }
                }else{
                    value = defaults.containsKey(format[i]) ? defaults.get(format[i]) : "";
                }
                
                map.put(format[i], value);
            }
            
            return map;
        }
        
        
}
