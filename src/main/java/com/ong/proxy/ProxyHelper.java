package com.ong.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ong.bean.helpers.BeanHelper;

/**
 * 代理帮助类
 * 
 * @Description: 代理帮助类
 * @Author: Ong
 * @CreateDate: 2017-09-25 14:00:00
 * @E-mail: 865208597@qq.com
 */
public class ProxyHelper {
	
	private static Logger logger = LoggerFactory.getLogger(ProxyHelper.class);
	
	/**
	 * 代理的请求参数
	 */
	public static final String REQPARAM = "reqParam";
	
	/**
	 * 代理的请求参数类型
	 */
	public static final String REQPARAMCLAZZ = "reqParamClazz";
	
	/**
	 * 代理的响应参数
	 */
	public static final String RESPPARAM = "respParam";
	
	/**
	 * 反射执行
	 * @param className
	 * @param methodName
	 * @param paramContext
	 * @return
	 */
	public static boolean doInvokeByClass(String className, String methodName, Map<String,Object> paramContext){
		Class<?> clazz;
		Object targetObj;
		try {
			clazz = Class.forName(className);
			targetObj = clazz.newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("doInvokeByClass Exception {}",e);
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		}
		
		return doInvokeByObject(targetObj, methodName, paramContext);
	}
	
	public static boolean doInvokeByObject(Object targetObj, String methodName, Map<String,Object> paramContext){
		try {
			Class<? extends Object> clazz = targetObj.getClass();
			Method method = null;
			Object invokeResult = null;
			Object reqParams = paramContext.get(REQPARAM);
			if(reqParams == null){
				method = clazz.getDeclaredMethod(methodName);
				invokeResult = method.invoke(targetObj);
			}else{
				Class<?>[] reqClazzArray = null;
				Object object = paramContext.get(REQPARAMCLAZZ);
				if(object == null){
					Object[] reqArray = BeanHelper.transObj2ObjArray(reqParams);
					reqClazzArray = BeanHelper.getObjClazz(reqArray);
				}else{
					if(object instanceof java.util.List){
						@SuppressWarnings("unchecked")
						List<Object> objList = (List<Object>)object;
						if(objList.size() == 0){
							//class 是 空 则认为去 无参数调用
							method = clazz.getDeclaredMethod(methodName);
							invokeResult = method.invoke(targetObj);
							paramContext.put(RESPPARAM, invokeResult);
							return true;
						}
						reqClazzArray = new Class<?>[objList.size()];
						for(int i =0 ;i<objList.size();i++){
							reqClazzArray[i]=(Class<?>) objList.get(i);
						}
//						reqClazzArray = BeanHelper.getObjClazz(objList.toArray());
					}else{
						reqClazzArray = (Class<?>[])object;
					}
					
				}
				method = clazz.getDeclaredMethod(methodName,reqClazzArray);
				invokeResult = method.invoke(targetObj,BeanHelper.transObj2ObjArray(paramContext.get(REQPARAM)));
			}
			paramContext.put(RESPPARAM, invokeResult);
			return true;
		} catch (IllegalAccessException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			logger.error("doInvoke Exception {}",e);
			e.printStackTrace();
			return false;
		}
		
	}
	
}
