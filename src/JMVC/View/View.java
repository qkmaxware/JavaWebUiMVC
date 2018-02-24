/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.View;

import JMVC.Controller.Actions.Http.HttpResponse;
import JMVC.Controller.Actions.Http.HttpStatusCode;
import JMVC.Controller.Actions.Http.Mime;
import JMVC.Server.ServerLogger;
import JMVC.View.Bags.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import jdk.nashorn.api.scripting.ClassFilter;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

/**
 *
 * @author chalseth
 */
public class View extends JMVC.Controller.Actions.Action{
    
    private static Pattern codeTag = Pattern.compile("<@>((.|\\n)*?)<\\/@>");
    private String html;
    
    private ViewBag traitsBag;
    
    public View(Template template){
        this(template, new ViewBag());
    }
    
    public View(Template template, ViewBag traits){
        //The view's job is to convert the template to a webpage
        String raw = template.GetTemplatedText();
        this.traitsBag = traits;
        
        Matcher m = codeTag.matcher(raw);
        StringBuilder compiled = new StringBuilder();
        int offset = 0;
        while(m.find()){
            
            int start = m.start();
            int end = m.end();
            String code = m.group(1);
            
            //Evaluate code and get result
            String result = EvaluateScript(code);
            
            //Construct final text
            compiled.append(raw.substring(offset, start));
            compiled.append(result);
            offset = end;
        }
        compiled.append(raw.substring(offset));
        
        html = compiled.toString();
    }
    
    private String EvaluateScript(String script){
        if(script == null)
            return "";
        script = script.trim();
        try {
            ClassFilter filter = (String string) -> false;
            
            NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
            ScriptEngine engine = factory.getScriptEngine(filter);
            
            //Set bound objects
            engine.put("View", this.traitsBag);
            engine.put("Html", new HtmlBag());
            engine.put("Css", new CssBag());
            engine.put("Url", new UrlBag());
            engine.put("Js", new JsBag());
            
            Object result = engine.eval(script);
            if(result == null){
                return "";
            }else{
                return result.toString();
            }
        } catch (Exception ex) {
            ServerLogger.Log(ex);
            return "";
        }
    }
    
    @Override
    public String GetMessage(){
        HttpResponse r = new HttpResponse();
        r.SetHttpStatus(HttpStatusCode.SUCCESS_200);
        r.SetContentType(Mime.HTML);
        r.SetBody(html);
        
        return r.GetResponse();
    }
    
}
