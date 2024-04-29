package com.springwebapp.myfirstspringwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	// sayHello => "Hello, World"
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you are learning today ?";
	}
	
	
	
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {	
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>My First HTML Page</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My First html page with body");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	
	// say-Hello.jsp => "Hello, World"
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}

	
	
}
