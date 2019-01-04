package com.yyy.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.model.Disease;
import com.yyy.model.Symptom;
import com.yyy.service.IndexService;
import com.yyy.service.impl.IndexServiceImpl;

@Controller
public class SelfExaminationAction {

	IndexService is = new IndexServiceImpl();

	@RequestMapping(value = "selfSelect")
	@ResponseBody
	public List<Disease> selfSelect(String field, String[] value, int[] qzvalue) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Disease> disList = null;
		Set<String> strSet = null;
		
		Map<List<Disease>, Integer> disMap = null;
		String[] str2 = new String[value.length];
		String[] str1 = new String[value.length];
		String[] str3 = new String[value.length];
		for (int i = 0; i < value.length; i++) {
			String temp = "\"" + value[i] + "\"";
			map.put(value[i], qzvalue[i]);
			str2[i] = temp;
			str1[i] = "dsympton";
			str3[i] = "SHOULD";
		}
		
		
		try {
			disMap = is.getAllBooleanQuery(1, 20, str1, str2, str3, 1, "ALL");
			for (List<Disease> list : disMap.keySet()) {
				disList = list;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置参数
		if (null != disList && disList.size()>0) {
			for (int i = 0; i < disList.size(); i++) {
				double score = 0;
				double total = 0;
				strSet = new HashSet<String>();
				List<Symptom> syList = disList.get(i).getSymList();
				
				for(int k=1;k<syList.size();k++){
					String str = syList.get(k).getSname();
					str = str.replaceAll("</?[a-zA-Z]+[^><]*>", "").trim();//清空添加的样式
					strSet.add(str);
				}
				
				for (int j = 0; j < value.length; j++) {
					total += qzvalue[j];
					
					if (strSet.contains(value[j].trim())) {
						score +=  qzvalue[j];
						strSet.remove(value[j]);
					}
				}
				double result = score / total;
				int result1 = (int) Math.round(result*10000);
				disList.get(i).setProbability(result1);
				
			}
			//排序
			for(int i = 0; i<disList.size()-1;i++){
				for(int j= i+1 ;j<disList.size();j++){
					if(disList.get(j-1).getProbability()<=disList.get(j).getProbability()){
						Disease temp = disList.get(j-1);
						disList.set(j-1, disList.get(j));
						disList.set(j, temp);
					}
				}
			}
			disList.get(0).setMap(map);
			disList.get(0).setStrSet(strSet);
		}
		
		return disList;
	}
	
	@RequestMapping(value="getSuggest")
	@ResponseBody
	public List<String> getSuggest(String field,String value){
		
		List<Symptom> syList = null;
		List<String> strList = new ArrayList<String>();
		Map<List<Symptom>,Integer> map = null;
		
		try {
			map = is.getListSymptomByQP(1,3,field,value);
			for(List<Symptom> list :map.keySet()){
				syList = list;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != syList){
			for(Symptom sy:syList){
				strList.add(sy.getSname());
			}
		}
		return strList;
	}
	
	@RequestMapping(value="verification")
	@ResponseBody
	public List<String> verification(String field,String value){
		
		List<String> strList = new ArrayList<String>();
		Map<List<Symptom>,Integer> map = null;
		String value1 = "\"" + value + "\"";
		try {
			map = is.getListSymptomByQP(1,3,field,value1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(map.size()>0){
			
		}else{
			strList = null;
		}
		return strList;
	}
}
