/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server.Request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Endpoint {
    public String action() default "";
    public RequestType[] types() default {RequestType.GET};
    public boolean enabled() default true;
}
 