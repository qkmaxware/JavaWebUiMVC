/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Server.Request;

/**
 *
 * @author chalseth
 */
public enum RequestType {
    NONE(), 
    GET(), 
    POST(), 
    HEAD(), 
    PUT(), 
    DELETE(), 
    OPTIONS(), 
    CONNECT();
    
    RequestType(){}

}