package com.yyy.service;

import java.util.List;

import com.yyy.model.ChineseMedicine;
import com.yyy.model.Dis_details;
import com.yyy.model.Disease;
import com.yyy.model.Prescription;
import com.yyy.model.Sy_details;
import com.yyy.model.Symptom;
import com.yyy.model.User;


public interface SQLService {
	
	/**
	 * 此方法将查询的用于初始化索引库
	 * @return
	 */
	public List<Disease> initDisease();
	public List<Symptom> initSymptom();
	public List<ChineseMedicine> initChineseMedicine();
	public List<Prescription> initPrescription();
	
	/**
	 * 分页操作
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Disease> diseasePage(int start,int end);
	public int pageMaxNum();
	
	public List<Symptom> symptomPage(int start,int end);
	public int symptomPageMaxNum();
	
	public List<ChineseMedicine> chineseMedicinePage(int start,int end);
	public int cmPageMaxNum();
	
	public List<Prescription> prescriptionPage(int start,int end);
	public int prePageMaxNum();
	
	/**
	 * 获取详细信息
	 * @param did
	 * @return
	 */
	public Dis_details getDisDetails(int did);
	public Sy_details getSyDetails(int sid);
	public ChineseMedicine getCMDetails(int mid);
	public Prescription getPreDetails(int pid);
	
	/**
	 * user相关操作
	 * @param user
	 * @return
	 */
	public int register(User user);
	public User selectById(int uid);
	public int update(User user);
	
	public User login(int account,String pw);
}
