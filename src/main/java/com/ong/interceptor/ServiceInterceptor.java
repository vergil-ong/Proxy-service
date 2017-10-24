package com.ong.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ong.SpringContextHelper;
import com.ong.bean.helpers.BeanHelper;
import com.ong.interfaceupgrade.InterfaceUpgradeHolder;
import com.ong.mapper.InterfaceMapper;
import com.ong.proxy.ProxyHelper;

@Aspect
@Component
public class ServiceInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);
	
	@Pointcut("execution(* com.ong.service..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void serviceMethodPointcut() {

	}
	
	@Around("serviceMethodPointcut()")
	public Object Interceptor(ProceedingJoinPoint pjp){
		String targetName = pjp.getTarget().getClass().getName();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		
		List<InterfaceMapper> interfaceMappers = InterfaceUpgradeHolder.getInterfaceMappers();
		if(interfaceMappers == null){
			return doOriginalMethod(pjp);
		}
		for(InterfaceMapper mapper : interfaceMappers){
			if(targetName.contains(mapper.getOriginalClassName())
					&&signature.getMethod().getName().equals(mapper.getOriginalMethodName())){
				//要切入的类和方法
				Class<?>[] objClazz = BeanHelper.getObjClazz(pjp.getArgs());
				List<Class<?>> originalArgsType = mapper.getOriginalArgsType();
				List<Class<?>> objClazzList = Arrays.asList(objClazz);
				if(!originalArgsType.equals(objClazzList)){
					continue;
				}
				
				logger.info("start aop");
				
				Object currentBean = SpringContextHelper.getBean(mapper.getCurrentClazz());
				Map<String,Object> paramContext = new HashMap<String,Object>();
				paramContext.put(ProxyHelper.REQPARAMCLAZZ, mapper.getCurrentArgsType());
				
				Object[] args = pjp.getArgs();
				if(args == null){
					//先匹配现有参数，如不存在，则匹配defaultValue
					paramContext.put(ProxyHelper.REQPARAM, mapper.getCurrentArgs());
				}else{
					List<Object> reqParams = new ArrayList<Object>(mapper.getCurrentArgs().size());
					reqParams.addAll(mapper.getCurrentArgs());
					List<Class<?>> currentArgsType = mapper.getCurrentArgsType();
					List<Object> currentArgs = mapper.getCurrentArgs();
					for(int i=0;i<args.length;i++){
						Object arg = args[i];
						Class<?> currentClass = currentArgsType.get(i);
						if(!arg.getClass().equals(currentClass)){
							reqParams.set(i, currentArgs.get(i));
//							reqParams.add(currentArgs.get(i));
						}else{
							reqParams.set(i, arg);
//							reqParams.add(arg);
						}
					}
					paramContext.put(ProxyHelper.REQPARAM, reqParams);
				}
				
				boolean result = ProxyHelper.doInvokeByObject(currentBean, mapper.getCurrentMethodName(), paramContext);
				if(result){
					return paramContext.get(ProxyHelper.RESPPARAM);
				}else{
					return null;
				}
			}
		}
		
		/*if(targetName.contains("com.ong.service.TestService")&&signature.getMethod().getName().equals("test2")){
			//要切入的类和方法
			logger.info("start aop");
			TestService testService = SpringContextHelper.getBean(TestService.class);
			Map<String,Object> paramContext = new HashMap<String,Object>();
			boolean result = ProxyHelper.doInvokeByObject(testService, "test1", paramContext);
			if(result){
				return paramContext.get(ProxyHelper.RESPPARAM);
			}else{
				return null;
			}
		}*/
		return doOriginalMethod(pjp);
	}
	
	public Object doOriginalMethod(ProceedingJoinPoint pjp){
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}

