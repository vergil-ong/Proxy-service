package com.ong.controller;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ong.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test1")
	@ResponseBody
	public Map<String,Object> test1(HttpServletRequest request){
		logger.info("this is test1 controller");
		logger.info("hostname is {}",request.getHeader("host"));
		InputStream inputStream = this.getClass().getResourceAsStream("/D://team.txt");
		logger.info("inputStream {}",inputStream);
		return testService.test1();
	}
	
	@RequestMapping("/test1/{name}")
	@ResponseBody
	public Map<String,Object> test1(@PathVariable String name){
		logger.info("this is test1 controller");
		return testService.test1(name);
	}
}
