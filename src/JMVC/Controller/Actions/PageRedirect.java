/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Controller.Actions;

import JMVC.Controller.Actions.Http.HttpResponse;
import JMVC.Controller.Actions.Http.HttpStatusCode;
import JMVC.Controller.Actions.Http.Mime;
import JMVC.Server.MapRoute;

/**
 *
 * @author halse
 */
public class PageRedirect extends Action{

    private String url = "";
    
    public PageRedirect(String Controller, String Action){
        this.url = MapRoute.Build(Controller, Action);
    }
    
    public PageRedirect(String absoluteUrl){
        this.url = absoluteUrl;
    }
    
    @Override
    public String GetMessage() {
        HttpResponse r = new HttpResponse();
        r.SetHttpStatus(HttpStatusCode.REDIRECT_307);
        r.SetContentType(Mime.HTML);
        r.SetHeader("Location", url);
        r.SetBody("");
        return r.GetResponse();
    }
    
}
