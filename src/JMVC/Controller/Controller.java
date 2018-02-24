/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Controller;

import JMVC.Controller.Actions.Action;
import JMVC.Controller.Actions.NotFoundErrorAction;
import JMVC.Server.Request.*;
import JMVC.Server.Request.Request;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 *
 * @author chalseth
 */
public class Controller {
    
    public Action Invoke(Request request, String action) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        Action fourOhfour = new NotFoundErrorAction();

        //Get method with name
        Method bestFit = null;
        int matchedParameters = Integer.MIN_VALUE;
        Object[] parameters = new Object[0];
        
        //Get all methods
        Method[] methods = this.getClass().getMethods();
        for(Method method : methods){
            
            //Ignore non-remote methods and methods with wrong output class
            Remote remote = method.getAnnotation(Remote.class);
            if(remote != null && Action.class.isAssignableFrom(method.getReturnType())){
                
                //Make sure enabled, method action name and valid http request types match
                String actionName = (remote.action() == null || remote.action().isEmpty()) ? method.getName() : remote.action();
                if(remote.enabled() && actionName.equals(action) && Arrays.asList(remote.types()).contains(request.type)){

                    //Find how many parameters in this method match request params
                    int matches = 0;
                    boolean matchedAll = true;
                    Parameter[] myParams = method.getParameters();
                    Object[] myValues = new Object[myParams.length];
                    for(int i = 0; i < myParams.length; i++){
                        Parameter param = myParams[i];
                        String name = param.getName();
                        if(request.parameters.containsKey(name)){
                            matches ++;
                            myValues[i] = request.parameters.get(name);
                        }else{
                            matchedAll = false;
                            break;
                        }
                    }
                    
                    //If this match is better (more parameters matched) this is the best one
                    if(matchedAll && matches > matchedParameters){
                        matchedParameters = matches;
                        bestFit = method;
                        parameters = myValues;
                    }
                }
            }
        }
        
        //Invoke the action
        if(bestFit == null)
            return fourOhfour;
        Action result = (Action)bestFit.invoke(this, parameters);

        return result;
    }
    
}
