package com.ong.xml;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ong.file.ReadFile;
import com.ong.mapper.InterfaceMapper;

/**
 * 对XML类的帮助类
 * @Description:  XML类的帮助类
 * @Author:       Ong
 * @CreateDate:   2017-10-17 15:00:00
 * @E-mail:		  865208597@qq.com
 */
public class XMLHelper {
	
	private static Logger logger = LoggerFactory.getLogger(XMLHelper.class);
	
	public static final String DEFAULTXML = "inter-upgrade.xml";
	
	public static String getStrStaticData(String fileName) {
		URL resourceURL = XMLHelper.class.getClassLoader().getResource(fileName);
		logger.info("resourceURL is {}",resourceURL);
		String fileStr = resourceURL.getFile();
		File file = new File(fileStr);
		if(!file.exists()){
			//文件不存在
			logger.info("file not exist {}",fileStr);
			return null;
		}
		
		return ReadFile.readFileByLines(fileStr);
	}
	
	public static Document getXMLData(String file) {
		String strStaticData = getStrStaticData(file);
		try {
			Document parseText = DocumentHelper.parseText(strStaticData);
			return parseText;
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error("Exception {}",e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<InterfaceMapper> parseXMLMapper(Document document){
		List<InterfaceMapper> list = new ArrayList<InterfaceMapper>();
		if(document == null){
			return list;
		}
		Element interfacesEle = document.getRootElement();
//		Element interfacesEle = rootElement.element("interfaces");
		List<Element> interfaceList = interfacesEle.elements("interface");
		for(Element interfaceEle : interfaceList){
			InterfaceMapper interMapper = new InterfaceMapper();
			Element originalEle = interfaceEle.element("original");
			interMapper.setOriginalClassName(originalEle.attributeValue("class-name"));
			try {
				interMapper.setOriginalClazz(Class.forName(interMapper.getOriginalClassName()));
			} catch (ClassNotFoundException e) {
				logger.error("ClassNotFound {}",e);
			}
			interMapper.setOriginalMethodName(originalEle.attributeValue("method-name"));
			List<Element> argumentList = originalEle.elements("argument");
			if(argumentList != null){
				List<Class<?>> argTypeList = new ArrayList<Class<?>>(argumentList.size());
				for(Element arg : argumentList){
					String type = arg.attributeValue("type");
					Class<?> clazz = null;
					try {
						clazz = Class.forName(type);
					} catch (ClassNotFoundException e) {
						logger.error("ClassNotFound {}",e);
					}
					
					argTypeList.add(clazz);
				}
				interMapper.setOriginalArgsType(argTypeList);
			}
			
			Element currentEle = interfaceEle.element("current");
			interMapper.setCurrentClassName(currentEle.attributeValue("class-name"));
			try {
				interMapper.setCurrentClazz(Class.forName(interMapper.getCurrentClassName()));
			} catch (ClassNotFoundException e) {
				logger.error("ClassNotFound {}",e);
			}
			interMapper.setCurrentMethodName(currentEle.attributeValue("method-name"));
			argumentList = null;
			argumentList = currentEle.elements("argument");
			if(argumentList != null){
				List<Class<?>> argTypeList = new ArrayList<Class<?>>(argumentList.size());
				List<Object> argValueList = new ArrayList<Object>(argumentList.size());
				for(Element arg : argumentList){
					String type = arg.attributeValue("type");
					Class<?> clazz = null;
					try {
						clazz = Class.forName(type);
					} catch (ClassNotFoundException e) {
						logger.error("ClassNotFound {}",e);
					}
					argTypeList.add(clazz);
					argValueList.add(arg.attributeValue("default-value") == null?"":arg.attributeValue("default-value"));
				}
				interMapper.setCurrentArgsType(argTypeList);
				interMapper.setCurrentArgs(argValueList);
			}
			list.add(interMapper);
		}
		
		return list;
	}
	
	public static List<InterfaceMapper> parseXMLMapper(){
		return parseXMLMapper(getXMLData(DEFAULTXML));
	}
	
}
