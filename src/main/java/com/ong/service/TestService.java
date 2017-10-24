package com.ong.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);
	
	public Map<String,Object> test1(){
		logger.info("this is TestService`s test1");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", "0");
		result.put("message", "this is test1");
		return result;
	}
	
	public Map<String,Object> test1(String name){
		logger.info("this is TestService`s test1");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", "0");
		result.put("message", "this is test1");
		result.put("name", name);
		return result;
	}
	
	public Map<String,Object> test2(){
		logger.info("this is TestService`s test2");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", "0");
		result.put("message", "this is test2");
		return result;
	}
	
	public Map<String,Object> test2(String name){
		logger.info("this is TestService`s test2");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", "0");
		result.put("message", "this is test2");
		result.put("name", name);
		return result;
	}
	
	public Map<String,Object> test3(String name,String code){
		logger.info("this is TestService`s test3");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", code);
		result.put("message", "this is test3");
		result.put("name", name);
		return result;
	}
	
}
