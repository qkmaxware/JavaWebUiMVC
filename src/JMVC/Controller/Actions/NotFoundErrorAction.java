/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Controller.Actions;

import JMVC.Controller.Actions.Http.HttpResponse;
import JMVC.Controller.Actions.Http.HttpStatusCode;
import JMVC.Controller.Actions.Http.Mime;

/**
 *
 * @author halse
 */
public class NotFoundErrorAction extends Action{

    @Override
    public String GetMessage() {
        HttpResponse r = new HttpResponse();
        r.SetHttpStatus(HttpStatusCode.CLIENT_ERR_400);
        r.SetContentType(Mime.HTML);
        r.SetBody("<html><head></head><body>File not found</body></html>");
        return r.GetResponse();
    }
    
}
