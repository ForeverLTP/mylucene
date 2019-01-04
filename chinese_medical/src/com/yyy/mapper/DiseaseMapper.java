package com.yyy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyy.model.Dis_details;
import com.yyy.model.Disease;
import com.yyy.model.Sy_details;
import com.yyy.model.Symptom;

public interface DiseaseMapper {
	
	/**
	 * Lucene初始化
	 * @return
	 */
	public List<Disease> initDisease();
	public List<Symptom> initSymptom();
	/**
	 * 分页操作
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Disease> diseasePage(@Param("start") int start, @Param("end") int end);
	public int pageMaxNum();
	public List<Symptom> symptomPage(@Param("start") int start, @Param("end") int end);
	public int symptomPageMaxNum();
	
	/**
	 * 获取疾病详细信息
	 * @param did
	 * @return
	 */
	public Dis_details getDisDetails(int did);
	/**
	 * 获取疾病详细信息
	 * @param did
	 * @return
	 */
	public Sy_details getSyDetails(int sid);
}
