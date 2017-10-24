package com.ong.mapper;

import java.util.List;

/**
 * 对接口的映射类
 * @Description:  对接口的映射类
 * @Author:       Ong
 * @CreateDate:   2017-10-17 15:00:00
 * @E-mail:		  865208597@qq.com
 */
public class InterfaceMapper {
	
	private Class<?> originalClazz;
	
	private Class<?> currentClazz;
	
	private String originalClassName;
	
	private String currentClassName;
	
	private String originalMethodName;
	
	private String currentMethodName;
	
	private List<Class<?>> originalArgsType;
	
	private List<Class<?>> currentArgsType;
	
	private List<Object> originalArgs;
	
	private List<Object> currentArgs;

	/**
	 * @return the originalClazz
	 */
	public Class<?> getOriginalClazz() {
		return originalClazz;
	}

	/**
	 * @param originalClazz the originalClazz to set
	 */
	public void setOriginalClazz(Class<?> originalClazz) {
		this.originalClazz = originalClazz;
	}

	/**
	 * @return the currentClazz
	 */
	public Class<?> getCurrentClazz() {
		return currentClazz;
	}

	/**
	 * @param currentClazz the currentClazz to set
	 */
	public void setCurrentClazz(Class<?> currentClazz) {
		this.currentClazz = currentClazz;
	}

	/**
	 * @return the originalClassName
	 */
	public String getOriginalClassName() {
		return originalClassName;
	}

	/**
	 * @param originalClassName the originalClassName to set
	 */
	public void setOriginalClassName(String originalClassName) {
		this.originalClassName = originalClassName;
	}

	/**
	 * @return the currentClassName
	 */
	public String getCurrentClassName() {
		return currentClassName;
	}

	/**
	 * @param currentClassName the currentClassName to set
	 */
	public void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}

	/**
	 * @return the originalMethodName
	 */
	public String getOriginalMethodName() {
		return originalMethodName;
	}

	/**
	 * @param originalMethodName the originalMethodName to set
	 */
	public void setOriginalMethodName(String originalMethodName) {
		this.originalMethodName = originalMethodName;
	}

	/**
	 * @return the currentMethodName
	 */
	public String getCurrentMethodName() {
		return currentMethodName;
	}

	/**
	 * @param currentMethodName the currentMethodName to set
	 */
	public void setCurrentMethodName(String currentMethodName) {
		this.currentMethodName = currentMethodName;
	}

	/**
	 * @return the originalArgsType
	 */
	public List<Class<?>> getOriginalArgsType() {
		return originalArgsType;
	}

	/**
	 * @param originalArgsType the originalArgsType to set
	 */
	public void setOriginalArgsType(List<Class<?>> originalArgsType) {
		this.originalArgsType = originalArgsType;
	}

	/**
	 * @return the currentArgsType
	 */
	public List<Class<?>> getCurrentArgsType() {
		return currentArgsType;
	}

	/**
	 * @param currentArgsType the currentArgsType to set
	 */
	public void setCurrentArgsType(List<Class<?>> currentArgsType) {
		this.currentArgsType = currentArgsType;
	}

	/**
	 * @return the originalArgs
	 */
	public List<Object> getOriginalArgs() {
		return originalArgs;
	}

	/**
	 * @param originalArgs the originalArgs to set
	 */
	public void setOriginalArgs(List<Object> originalArgs) {
		this.originalArgs = originalArgs;
	}

	/**
	 * @return the currentArgs
	 */
	public List<Object> getCurrentArgs() {
		return currentArgs;
	}

	/**
	 * @param currentArgs the currentArgs to set
	 */
	public void setCurrentArgs(List<Object> currentArgs) {
		this.currentArgs = currentArgs;
	}
	
	
}
