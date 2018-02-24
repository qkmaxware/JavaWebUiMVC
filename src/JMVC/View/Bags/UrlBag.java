/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.View.Bags;

import JMVC.Server.MapRoute;

/**
 *
 * @author halse
 */
public class UrlBag {
    
    public String ActionUrl(String controller, String action){
        return "/" + MapRoute.Build(controller, action);
    }
    
}
