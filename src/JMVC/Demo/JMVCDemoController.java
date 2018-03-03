package JMVC.Demo;


import JMVC.Controller.Actions.Action;
import JMVC.Controller.Actions.Json;
import JMVC.Controller.Controller;
import JMVC.Server.Request.Request;
import JMVC.Server.Request.RequestType;
import JMVC.View.Bags.ViewBag;
import JMVC.View.Template;
import JMVC.View.View;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import JMVC.Server.Request.Endpoint;
import JMVC.Server.Request.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chalseth
 */
public class JMVCDemoController extends Controller{
    
    Template _layoutBase = new Template(new File("_layout.html"));
    Template _layoutMain = new Template(new File("_layoutMain.html"));
    Template indexTemplate = new Template(new File("index.html"));
    Template loginTemplate = new Template(new File("login.html"));
    
    public class LoginResponse {
        public String token;
        public boolean success = false;
    }
    
    public JMVCDemoController(){
        loginTemplate.SetParent(_layoutBase);
        indexTemplate.SetParent(_layoutMain);
        
        _layoutMain.SetParent(_layoutBase);
        
    }
    
    @Endpoint()
    public Action Index(){
        ViewBag traits = new ViewBag();
        
        traits.set("names", new ArrayList<String>(Arrays.asList("John","Ken", "Phil")));
        traits.set("title", "Home");
        return new View(indexTemplate, traits);
    }

    @Endpoint(
        types = {
            RequestType.GET, 
            RequestType.POST
        },
        map = {
            @Map(name = "request", index = 0)
        }
    )
    public Action Api(String request){
        LoginResponse res = new LoginResponse();
        res.token = "Hello "+request;
        res.success = true;
        return new Json(res.token);
    }
    
}
