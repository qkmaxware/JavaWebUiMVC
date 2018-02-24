/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Controller.Actions;

import JMVC.Controller.Actions.Http.HttpResponse;
import JMVC.Controller.Actions.Http.HttpStatusCode;
import JMVC.Controller.Actions.Http.Mime;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 *
 * @author halse
 */
public class Json extends Action{
    
    private String json;
    
    private Json(){}
    
    private static String Encode(Class clazz, Object value){
        //Null
        if(value == null){
            return "null";
        }
        //Number
        else if(
            clazz.equals(Double.class) || 
            clazz.equals(Float.class) || 
            clazz.equals(Integer.class) ||
            clazz.equals(Long.class)
        ){
            return value.toString();
        }
        //Boolean
        else if(
            clazz.equals(Boolean.class)
        ){
            return value.toString();
        }
        //String
        else if(
            clazz.equals(String.class)
        ){
            return "\"" + value.toString() + "\"";
        }
        //Arrays
        else if (
            clazz.isArray()
        ){
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            int length = Array.getLength(value);
            for (int i = 0; i < length; i ++) {
                Object arrayElement = Array.get(value, i);
                if(i != 0){
                    builder.append(",");
                }
                builder.append(Encode(arrayElement.getClass(), arrayElement));
            }
            builder.append("]");
            return builder.toString();
        }
        //Object
        else{
            StringBuilder builder = new StringBuilder();
            builder.append("{");

            boolean first = true;
            Field[] fields = clazz.getFields();
            for(Field field : fields){
                String name = field.getName();
                try {
                    Object fieldvalue = field.get(value);
                    Class valueClass = fieldvalue.getClass();

                    if(!first){
                        builder.append(",");
                    }

                    builder.append(name);
                    builder.append(": ");
                    builder.append(Encode(valueClass, fieldvalue));

                    first = false;
                } catch (Exception ex) {}
                
            }
            builder.append("}");
            
            return builder.toString();
        }
    }
    
    public Json(Object object){
        this.json = Encode(object.getClass(), object);
    }

    @Override
    public String GetMessage(){
        HttpResponse r = new HttpResponse();
        r.SetHttpStatus(HttpStatusCode.SUCCESS_200);
        r.SetContentType(Mime.JSON);
        r.SetBody(json);
        return r.GetResponse();
    }
    
}
