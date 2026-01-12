package com.spring.cicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CicdController {
	
	  @GetMapping("/hello")
	    public String hello() {
	        return "Hello, REST API!";
	    }

}
