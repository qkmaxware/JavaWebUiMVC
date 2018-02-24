/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.Controller.Actions.Http;

/**
 *
 * @author halse
 */
public enum Mime {
    
    //Text
    PLAIN_TEXT("text/plain"),
    HTML("text/html"),
    CSS("text/css"),
    JAVASCRIPT("text/javascript"),
    //Images
    JPEG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    BMP("image/bmp"),
    WEBP("image/webp"),
    //Audio
    MIDI("audio/midi"),
    MPEG("audio/mpeg"),
    WEBM_AUDIO("audio/webm"),
    OGG_AUDIO("audio/ogg"),
    WAV("audio/wav"),
    //Video
    MP4("video/mp4"),
    WEBM_VIDEO("video/webm"),
    OGG_VIDEO("video/ogg"),
    //Applications
    OCTET_STREAM("application/octet-stream"),
    PKCS12("application/pkcs12"),
    XHTML_XML("application/xhtml+xml"),
    XML("applicaion/xml"),
    PDF("application/pdf"),
    JSON("application/json")
    ;
    
    private String mime;
    
    Mime(String mime){
        this.mime = mime;
    }
    
    public String toString(){
        return mime;
    }
}
