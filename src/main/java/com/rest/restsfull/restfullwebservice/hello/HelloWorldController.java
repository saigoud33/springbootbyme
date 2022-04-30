package com.rest.restsfull.restfullwebservice.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource source;
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String getMessage() {
		return "Hello World..!!";
	}
	
	@GetMapping(path="/hellobean")
	public HelloWorldBean getMessageBean() {
		return new HelloWorldBean("Hi From Bean..");
	}
	
	@GetMapping(path="/hellobean/{name}")
	public HelloWorldBean getMessageBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hi Service from ..%s",name));
	}
	
	@GetMapping(path="/hello-world-i18n")
	public String getMessageI18n(
			@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return source.getMessage("hello.message",null, "Default Message",locale);
	}
}
