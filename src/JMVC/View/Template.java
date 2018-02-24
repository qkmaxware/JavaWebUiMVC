/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chalseth
 */
public class Template {
    public Template parent = null;
    private String html;
    
    Pattern child = Pattern.compile("((?:<@child\\/>)|(?:<@child>(?:.|\\n)*<\\/@child>))");
    
    public Template(String html){
        this.html = html;
    }
    
    public Template(String resource, String format){
        html = "";
        try{
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, format));
            
            
            for(String line; (line = reader.readLine()) != null;){
                html += line;
            }
        }
        catch(Exception e){}
    }
    
    public Template(File file){
        html = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for(String line; (line = reader.readLine()) != null;){
                html += line;
            }
        }
        catch(Exception e){}
    }
    
    public Template(Template t){
        this.html = t.html;
    }
    
    public void SetParent(Template t){
        this.parent = t;
    }
    
    public String GetTemplatedText(){
        if(parent != null){
            String pt = parent.GetTemplatedText();
            Matcher m = child.matcher(pt);
            pt = m.replaceAll(Matcher.quoteReplacement(html));
            return pt;
        }else{
            return html;
        }
    }
}
