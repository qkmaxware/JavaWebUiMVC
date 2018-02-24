# JMVC

Java Web Ui MVC (JMVC) is a simple web server written in Java for the explicit purpose of creating user interfaces for local applications by using HTML, CSS, and JavaScript.

## Setting Up HTML Templates

Pages are delivered to the web client in the form of HTML pages. For the most part, these web pages can be written as any other web page can, with some unique template difference.

The following HTML represents a basic webpage template. However, there are some unique parts to make a note of.
```
<!DOCTYPE html>
<html>
	<head>
		<title>
			<@>View.get("title")</@>
		</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
	</head>
</html>

<html>

<body>
	<div class="container">
		<@child>
		
		</@child>
	</div>
</body>
</html>
```

```
<@>View.get("title")</@>
```
This section of the template indicates that everything enclosed in the <@> tag will be replaced on the server by the results of the encapsulated JavaScript.

```
<@child>
		
</@child>
```
This tag indicates that if this page is a parent of another template that the child template will be inserted into it at this point.

From this we can see that the title of the returned webpage is generated on the server, and we can use this as a parent to another template. Our child template simply has a button that makes its own call to the webserver and can be seen below.
```
<button type="button", onClick="askServer();">
	Ask me
</button>

<script>

function askServer(){
	$.ajax({
		url: "<@>Url.ActionUrl('Home','Api')</@>",
		dataType: "json",
		data: {
			request: "apiCall"
		},
		success: function(result){
			console.log(result);
			var str = result;
			alert("Result: "+str);
		}
	});
}

</script>
```

## Creating a Controller to Deliver Templates

These templates by themselves are useless unless the webserver known how to deliver them to a client. This is done through objects that extend from the **Controller** class. A basic controller for our templates is shown below.

```
public class JMVCDemoController extends Controller{

	Template _layoutTemplate = new Template(new File("_layout.html"));
	Template indexTemplate = new Template(new File("index.html"));
	
	public JMVCDemoController(){
		//Configure the child template to have an encapsulating parent template
		indexTemplate.SetParent(_layout);
	}
	
	/**
	* This function represents the root index or start page of our application
	* It is accessible to the webserver because it is marked as an "Endpoint" which is by default accessible to GET requests
	*/
	@Endpoint()
	public Action Index(){
		//Set the page title as a View trait
		ViewBag traits = new ViewBag();
        traits.set("title", "Home");
		
		//Create a new view from the template and the configured traits
        return new View(indexTemplate, traits);
	}

	/**
	* This represents another webserver GET/POST accessible call-out. This one returns a JSON string rather than a view
	*/
	@Endpoint(
        types = {
            RequestType.GET, 
            RequestType.POST
        }
    )
    public Action Api(String request){
        return new Json("Hello "+request);
    }
	
}
```

This simple controller can respond to two different resource requests
 - Get /Index 
 - (Get or POST) /Api?request=value
 
The controller will receive a request for a resource and will attempt to find a matching method marked as an endpoint. This matching is done based on the number of parameters, the method name, and the HTTP request type (GET/POST/DELETE ect...).

## Starting the Webserver

Finally, we can start up our web server and start interacting with out code via a web-browser. To do this, simply create a **WebServer** object, assign it an instance of our controller, and start the server up.

```
public static void main(String[] args){

    //Create webserver on local port 80
    WebServer server = new WebServer(80);
    
    //Create and register controller
    JMVCDemoController HomeController = new JMVCDemoController();
    server.RegisterController("Home", HomeController);
    
    //Run the server, and try to open the browser to the index when it is done starting up
    try {
        server.Start(() -> {
            server.TryOpenBrowser();
        });
    } catch (WebServerException ex) {
        Logger.getLogger(JMVCDemo.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
```