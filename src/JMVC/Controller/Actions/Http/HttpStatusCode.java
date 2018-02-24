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
public enum HttpStatusCode {
    
    INFO_100(100, "Continue"),
    INFO_101(101, "Switching Protocols"),
    INFO_102(102, "Processing"),
    INFO_103(103, "Early Hints"),
    SUCCESS_200(200, "Ok"),
    SUCCESS_201(201, "Created"),
    SUCCESS_202(202, "Accepted"),
    SUCCESS_203(203, "Non-Authoritative Information"),
    SUCCESS_204(204, "No Content"),
    SUCCESS_205(205, "Reset Content"),
    SUCCESS_206(206, "Partial Content"),
    SUCCESS_207(207, "Multi-Status"),
    SUCCESS_208(208, "Already Reported"),
    SUCCESS_226(226, "IM Used"),
    REDIRECT_300(300, "Multiple Choices"),
    REDIRECT_302(302, "Found"),
    REDIRECT_303(303, "See Other"),
    REDIRECT_304(304, "Not Modified"),
    REDIRECT_305(305, "Use Proxy"),
    REDIRECT_306(306, "Switch Proxy"),
    REDIRECT_307(307, "Temporary Redirect"),
    REDIRECT_308(308, "Permanent Redirect"),
    CLIENT_ERR_400(400, "Bad Request"),
    CLIENT_ERR_401(401, "Unauthorized"),
    CLIENT_ERR_402(402, "Payment Required"),
    CLIENT_ERR_403(403, "Forbidden"),
    CLIENT_ERR_404(404, "NotFound"),
    CLIENT_ERR_405(405, "Method Not Allowed"),
    CLIENT_ERR_406(406, "Not Acceptable"),
    CLIENT_ERR_407(407, "Proxy Authentication Required"),
    CLIENT_ERR_408(408, "Request Timeout"),
    CLIENT_ERR_409(409, "Conflict"),
    CLIENT_ERR_410(410, "Gone"),
    CLIENT_ERR_411(411, "Length Required"),
    CLIENT_ERR_412(412, "Precondition Failed"),
    CLIENT_ERR_413(413, "Payload Too Large"),
    CLIENT_ERR_414(414, "URI Too Long"),
    CLIENT_ERR_415(415, "Unsupported Media Type"),
    CLIENT_ERR_416(416, "Range Not Satisfiable"),
    CLIENT_ERR_417(417, "Expectation Failed"),
    CLIENT_ERR_418(418, "I'm a Teapot"),
    CLIENT_ERR_421(421, "Misdirected Request"),
    CLIENT_ERR_422(422, "Unprocessable Entity"),
    CLIENT_ERR_423(423, "Locked"),
    CLIENT_ERR_424(424, "Failed Dependency"),
    CLIENT_ERR_426(426, "Upgrade Required"),
    CLIENT_ERR_428(428, "Precondition Required"),
    CLIENT_ERR_429(429, "Too Many Requests"),
    CLIENT_ERR_431(431, "Request Header Fields Too Large"),
    CLIENT_ERR_451(451, "Unavailable For Legal Reasons"),
    SERVER_ERR_500(500, "Internal Server Error"),
    SERVER_ERR_501(501, "Not Implemented"),
    SERVER_ERR_502(502, "Bad Gateway"),
    SERVER_ERR_503(503, "Service Unavailable"),
    SERVER_ERR_504(504, "Gateway Timeout"),
    SERVER_ERR_505(505, "HTTP Version Not Supported"),
    SERVER_ERR_506(506, "Variant Also Negotiates"),
    SERVER_ERR_507(507, "Insufficient Storage"),
    SERVER_ERR_508(508, "Loop Detected"),
    SERVER_ERR_510(510, "Not Extended"),
    SERVER_ERR_511(511, "Network Authentication Required")
    ;
    
    private int code;
    private String name;
    
    HttpStatusCode(int code, String name){
        this.code = code;
        this.name = name;
    }
    
    public int GetStatusCode(){
        return code;
    }
    
    public String GetName(){
        return name;
    }
    
}
