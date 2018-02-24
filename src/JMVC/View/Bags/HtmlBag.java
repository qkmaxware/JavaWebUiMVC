/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.View.Bags;

/**
 *
 * @author halse
 */
public class HtmlBag {

    public String DropDownFor(String name, Iterable<Object> list){
        StringBuilder b = new StringBuilder();
        b.append("<select name=\""+name+"\">");
        if(list != null)
            for(Object obj : list){
                String s = String.valueOf(obj);
                b.append("<option value=\"");
                b.append(s);
                b.append("\">");
                b.append(s);
                b.append("</option>");
            }
        b.append("</select>");
        return b.toString();
    }
    
    public String ActionButton(String name, String Controller, String Action){
        
        return "<button type=\"button\" action=\"/"+Controller+"/"+Action+"\">"+name+"</button>";
    }
    
}
