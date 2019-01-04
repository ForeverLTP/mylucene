package com.yyy.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;

import com.yyy.index.IndexUtils;
import com.yyy.index.SearchMethod;
import com.yyy.model.ChineseMedicine;
import com.yyy.model.Disease;
import com.yyy.model.Prescription;
import com.yyy.model.Symptom;
import com.yyy.service.IndexService;
import com.yyy.service.SQLService;

public class IndexServiceImpl implements IndexService {

	SearchMethod sm = new SearchMethod();
	@Override
	public Map<List<Disease>,Integer> getListDiseaseByQP(int pageIndex,int pageSize,String field, String value,int sign) throws Exception{
		  
		Map<List<Disease>,Integer> disMap = new HashMap<List<Disease>,Integer>();
		Map<Map<List<Document>,Integer>,Query> map = sm.searchByQueryParser(pageIndex,pageSize,field, value);
		List<Document> docList= null;
		Query query = null;
		int count = 0;
		for(Map<List<Document>,Integer> docMap:map.keySet()){
			query = map.get(docMap);
			for(List<Document> list:docMap.keySet()){
				docList = list;
				count = docMap.get(list);
				break;
			}
			break;
		}
		List<Disease> disList = IndexUtils.translateDis(docList, query, field,sign);
		disMap.put(disList, count);
		
		return disMap;
	
	}
	@Override
	public void creat(SQLService sqlService) {
		try {
			new IndexUtils().creatIndex(sqlService);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<List<Disease>, Integer> getAllBooleanQuery(int pageIndex,int pageSize,String[] str1,String[] str2,String[] str3,int sign,String field) throws Exception {
		Map<List<Disease>,Integer> disMap = new HashMap<List<Disease>,Integer>();
		Map<Map<List<Document>,Integer>,Query> map = sm.searcherByBooleanQuery(pageIndex, pageSize, str1, str2,str3);
		List<Document> docList= null;
		Query query = null;
		int count = 0;
		for(Map<List<Document>,Integer> docMap:map.keySet()){
			query = map.get(docMap);
			for(List<Document> list:docMap.keySet()){
				docList = list;
				count = docMap.get(list);
				break;
			}
			break;
		}
		List<Disease> disList = IndexUtils.translateDis(docList, query, field,sign);
		disMap.put(disList, count);
		
		return disMap;
	}
	@Override
	public Map<List<Symptom>, Integer> getListSymptomByQP(int pageIndex, int pageSize, String field, String value)
			throws Exception {
		Map<List<Symptom>,Integer> syMap = new HashMap<List<Symptom>,Integer>();
		Map<Map<List<Document>,Integer>,Query> map = sm.searchByQueryParser(pageIndex,pageSize,field, value);
		List<Document> docList= null;
		Query query = null;
		int count = 0;
		for(Map<List<Document>,Integer> docMap:map.keySet()){
			query = map.get(docMap);
			for(List<Document> list:docMap.keySet()){
				docList = list;
				count = docMap.get(list);
				break;
			}
			break;
		}
		List<Symptom> syList = IndexUtils.translateSy(docList, query, field);
		syMap.put(syList, count);
		
		
		return syMap;
	}
	@Override
	public Map<List<ChineseMedicine>, Integer> getListCMByQP(int pageIndex, int pageSize, String field, String value)
			throws Exception {
		Map<List<ChineseMedicine>,Integer> cmMap = new HashMap<List<ChineseMedicine>,Integer>();
		Map<Map<List<Document>,Integer>,Query> map = sm.searchByQueryParser(pageIndex,pageSize,field, value);
		List<Document> docList= null;
		Query query = null;
		int count = 0;
		for(Map<List<Document>,Integer> docMap:map.keySet()){
			query = map.get(docMap);
			for(List<Document> list:docMap.keySet()){
				docList = list;
				count = docMap.get(list);
				break;
			}
			break;
		}
		List<ChineseMedicine> cmList = IndexUtils.translateCM(docList, query, field);
		cmMap.put(cmList, count);
		
		
		return cmMap;
	}
	@Override
	public Map<List<Prescription>, Integer> getListPreByQP(int pageIndex, int pageSize, String field, String value)
			throws Exception {
		Map<List<Prescription>,Integer> preMap = new HashMap<List<Prescription>,Integer>();
		Map<Map<List<Document>,Integer>,Query> map = sm.searchByQueryParser(pageIndex,pageSize,field, value);
		List<Document> docList= null;
		Query query = null;
		int count = 0;
		for(Map<List<Document>,Integer> docMap:map.keySet()){
			query = map.get(docMap);
			for(List<Document> list:docMap.keySet()){
				docList = list;
				count = docMap.get(list);
				break;
			}
			break;
		}
		List<Prescription> preList = IndexUtils.translatePre(docList, query, field);
		preMap.put(preList, count);
		
		
		return preMap;
	}
	@Override
	public List<String> getSuggestion(String field, String value) {
		List<String> strList = null;
		try {
			strList = sm.suggestion(field, value);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strList;
	}
}
