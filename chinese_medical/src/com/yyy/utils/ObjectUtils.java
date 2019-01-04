package com.yyy.utils;

import java.util.ArrayList;
import java.util.List;

import com.yyy.index.IndexUtils;
import com.yyy.model.ChineseMedicine;
import com.yyy.model.Dis_details;
import com.yyy.model.Disease;
import com.yyy.model.Symptom;

public class ObjectUtils {

	/**
	 * 将数据切分成想象状态
	 * 
	 * @param disList
	 * @return
	 */
	public static List<Disease> getCompleteList(List<Disease> disList) {

		for (Disease dis : disList) {
			if (null != dis.getDdescription()) {
				String temp = dis.getDdescription();
				temp = temp.replace("[详细]", "");
				dis.setDdescription(temp);
			}
			if (null != dis.getDsympton()) {
				String[] spl = dis.getDsympton().split("相关症状");
				String symptom = spl[spl.length - 1];
				String[] symptoms = symptom.split("\\s+");
				List<Symptom> syList = new ArrayList<Symptom>();
				int flag = 0;
				for (String str : symptoms) {
					flag++;
					Symptom sy = new Symptom();
					sy.setSname(str);
					syList.add(sy);
					if (5 == flag) {
						break;
					}
				}
				dis.setSymList(syList);
			}

		}
		return disList;

	}

	public static List<Symptom> getCompleteListSy(List<Symptom> syList) {

		for (Symptom sy : syList) {
			if (null != sy.getSdescription()) {
				String temp = sy.getSdescription();
				temp = temp.replace("[详细]", "");
				sy.setSdescription(temp);
			}
			if (null != sy.getSdisease()) {
				String[] dis = sy.getSdisease().split("\\|");
				List<Disease> disList = new ArrayList<Disease>();
				for (String str : dis) {
					Disease disease = new Disease();
					disease.setDname(str);
					disList.add(disease);
				}
				sy.setDisList(disList);
			}

		}
		return syList;

	}
	
	/**
	 * 获取切割完整的疾病详细信息
	 * @param dd
	 * @return
	 */
	public static Dis_details getCompleteDisDetails(Dis_details dd){
		
		String introduce = dd.getDintroduce();
		String knowlege = dd.getDknowlege();
		String cure = dd.getDcure();
		List<String> list = null;
		
		String[] temp1 = introduce.split("\n");
		dd.setTitle1(temp1[0]);
		dd.setDintroduce(temp1[temp1.length -1]);
		
		knowlege = knowlege.replaceAll("\\[详细\\]", "");
		String[] str1 = knowlege.split("相关症状");
		String[] str2 = str1[str1.length-1].split("并发疾病");
		String[] str3 = str2[0].split("\\s+");
		String param = "";
		for(int i=1;i<str3.length;i++){
			param += "<a href=\"#\" >"+ str3[i] + "</a>&nbsp;&nbsp;";
		}
		knowlege = str1[0] + "相关症状：" + param + "\n并发疾病" + str2[str2.length-1];
		String[] param1 = knowlege.split("：");
		String value = "";
		for(int i=0;i<=param1.length-2;i++){
			String[] param2 = param1[i].split("\n");
			String text = "";
			for(int j=1;j<=param2.length-1;j++){//为什么从1开始，因为0有数据，判断1及以后是否有数据
				if(param2[j].trim().length()>0){
					text = param2[j];
					break;
				}
			}
			String[] param3 = param1[i+1].trim().split("\n");
			list = new ArrayList<String>();
			for(int j=0;j<param3.length;j++){
				if(param3[j].trim()!=""){
					list.add(param3[j]);
				}
			}
			String text3 = "";
			for(int j =0;j<list.size()-1;j++){
				text3 += list.get(j) + "&nbsp;&nbsp;";
			}
			
			if(i==0){
				//param2 =  param1[0].split("\n");
				dd.setTitle2(param2[0]);
			}
			value += "<br/>" + text + "：" + text3 ;
		}
		String[] param4 = param1[param1.length-1].split("\\s+");
		String param5 = "";
		for(String str:param4){
			param5 += str + "&nbsp;&nbsp;" ;
		}
		value = value +  param5;
		dd.setDknowlege(value);
		
		cure = cure.replaceAll("\\[详细\\]", "");
		String[] param6 = cure.split("：");
		String temp = "";
		for(int i=0;i<=param6.length-2;i++){
			String[] param7 = param6[i].trim().split("\n");
			String text1 = "";
			for(int j=param7.length-1;j>=1;j--){
				if(param7[j].trim()!=""){
					text1 = param7[j];
					break;
				}
			}
			String[] param8 = param6[i+1].trim().split("\n");
			list = new ArrayList<String>();
			for(int j=0;j<param8.length;j++){
				if(param8[j].trim()!=""){
					list.add(param8[j]);
				}
			}
			String text2 = "";
			for(int j =0;j<list.size()-1;j++){
				text2 += list.get(j) + "&nbsp;&nbsp;";
			}
			if(i==0){
				//param2 =  param1[0].split("\n");
				dd.setTitle3(param7[0]);
			}
			temp += "<br/>" + text1 + "：" + text2  ;
		}
		String[] param9 = param6[param6.length-1].split("\\s+");
		String param10 = "";
		for(String str:param9){
			param10 += str + "&nbsp;&nbsp;" ;
		}
		temp = temp + param10;
		dd.setDcure(temp);
		
		return dd;
		
	}
	public static List<ChineseMedicine> getCompleteCM(List<ChineseMedicine> cmList){
	
		for(ChineseMedicine cm:cmList){
			cm.setMap(IndexUtils.getCMData(cm.getMsexuality()));
		}
		
		return cmList;
		
	}

}
