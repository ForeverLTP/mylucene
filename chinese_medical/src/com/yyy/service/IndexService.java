package com.yyy.service;

import java.util.List;
import java.util.Map;

import com.yyy.model.ChineseMedicine;
import com.yyy.model.Disease;
import com.yyy.model.Prescription;
import com.yyy.model.Symptom;

public interface IndexService {
	/**
	 * 初始化索引库
	 * @param sqlService
	 */
	public void creat(SQLService sqlService);
	/**
	 * 单域查询
	 * @param pageIndex
	 * @param pageSize
	 * @param field
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Map<List<Disease>,Integer> getListDiseaseByQP(int pageIndex,int pageSize,String field,String value,int sign) throws Exception;
	//多域查询  占时不用
	public Map<List<Disease>,Integer> getAllBooleanQuery 
	(int pageIndex,int pageSize,String[] str1,String[] str2,String[] str3,int sign,String field) throws Exception;
	/**
	 * 单域查询 
	 * @param pageIndex
	 * @param pageSize
	 * @param field
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Map<List<Symptom>,Integer> getListSymptomByQP(int pageIndex,int pageSize,String field,String value) throws Exception;
	public Map<List<ChineseMedicine>,Integer> getListCMByQP(int pageIndex,int pageSize,String field,String value) throws Exception;
	public Map<List<Prescription>,Integer> getListPreByQP(int pageIndex,int pageSize,String field,String value) throws Exception;
	
	public List<String> getSuggestion(String field,String value);

}
