//package com.yyy.utils;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//public class ReflactUtils {
//	
//	/**
//	 * 反射
//	 * @param url
//	 * @return
//	 * @throws Exception
//	 */
//	public static Object getObject(String url) throws Exception{
//		Object o = Class.forName(url);
//		
//		 Method[] methodArray = o.getMethods();
//		for(Method method:methodArray){
//			System.out.println(method);
//		}
//		Object ob = o.getConstructor().newInstance();
//		Field[] fields = o.getDeclaredFields();
//		for(Field field:fields){
//			field.set(ob,"yyy");
//			System.out.println(field);
//		}
//		return ob;
//		
//	}
//
//}
