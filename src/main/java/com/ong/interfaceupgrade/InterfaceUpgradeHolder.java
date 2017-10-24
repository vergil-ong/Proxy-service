package com.ong.interfaceupgrade;

import java.util.List;

import com.ong.mapper.InterfaceMapper;
import com.ong.xml.XMLHelper;

public class InterfaceUpgradeHolder {
	
	private static ThreadLocal<List<InterfaceMapper>> localMapper = new ThreadLocal<List<InterfaceMapper>>();
	
	public static String xmlFileName = "";
	
	public static List<InterfaceMapper> getInterfaceMappers(){
		List<InterfaceMapper> list = localMapper.get();
		if(list != null){
			return list;
		}
		
		if("".equals(xmlFileName)){
			localMapper.set(XMLHelper.parseXMLMapper());
		}else{
			localMapper.set(XMLHelper.parseXMLMapper(XMLHelper.getXMLData(xmlFileName)));
		}
		return localMapper.get();
	}
	
	public static boolean setInterfaceMappers(List<InterfaceMapper> list){
		localMapper.set(list);
		return true;
	}
	
	public static boolean cleanup(){
		localMapper.remove();
		return true;
	}
	
	public static boolean refresh(){
		localMapper.remove();
		localMapper.set(XMLHelper.parseXMLMapper());
		return true;
	}
}
