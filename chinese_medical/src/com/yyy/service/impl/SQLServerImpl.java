package com.yyy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyy.mapper.ChineseMedicineMapper;
import com.yyy.mapper.DiseaseMapper;
import com.yyy.mapper.PrescriptionMapper;
import com.yyy.mapper.UserMapper;
import com.yyy.model.ChineseMedicine;
import com.yyy.model.Dis_details;
import com.yyy.model.Disease;
import com.yyy.model.Prescription;
import com.yyy.model.Sy_details;
import com.yyy.model.Symptom;
import com.yyy.model.User;
import com.yyy.service.SQLService;

@Service
public class SQLServerImpl implements SQLService {

	@Autowired
	DiseaseMapper dMapper;
	
	@Override
	public List<Disease> initDisease() {
		
		return dMapper.initDisease();
	}

	@Override
	public List<Symptom> initSymptom() {
		
		return dMapper.initSymptom();
	}

	@Override
	public List<Disease> diseasePage(int start,int end) {
		return dMapper.diseasePage(start, end);
	}

	@Override
	public int pageMaxNum() {
		return dMapper.pageMaxNum();
	}

	@Override
	public List<Symptom> symptomPage(int start, int end) {
		return dMapper.symptomPage(start, end);
	}

	@Override
	public int symptomPageMaxNum() {
		return dMapper.symptomPageMaxNum();
	}

	@Override
	public Dis_details getDisDetails(int did) {
		
		return dMapper.getDisDetails(did);
	}

	@Override
	public Sy_details getSyDetails(int sid) {
		return dMapper.getSyDetails(sid);
	}

	@Autowired
	ChineseMedicineMapper cmMapper;
	
	@Override
	public List<ChineseMedicine> initChineseMedicine() {
		return cmMapper.initChineseMedicine();
	}

	@Autowired
	PrescriptionMapper preMapper;
	@Override
	public List<Prescription> initPrescription() {
		return preMapper.initPrescription();
	}

	@Override
	public List<ChineseMedicine> chineseMedicinePage(int start, int end) {
		
		return cmMapper.chineseMedicinePage(start, end);
	}

	@Override
	public int cmPageMaxNum() {
		return cmMapper.cmPageMaxNum();
	}

	@Override
	public List<Prescription> prescriptionPage(int start, int end) {
		return preMapper.prescriptionPage(start, end);
	}

	@Override
	public int prePageMaxNum() {
		return preMapper.prePageMaxNum();
	}

	@Override
	public ChineseMedicine getCMDetails(int mid) {
		
		return cmMapper.getCMDetails(mid);
	}

	@Override
	public Prescription getPreDetails(int pid) {
		return preMapper.getPreDetails(pid);
	}

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int register(User user) {
		return userMapper.register(user);
	}

	@Override
	public User selectById(int uid) {
		return userMapper.searchById(uid);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public User login(int account, String pw) {
		return userMapper.login(account, pw);
	}

}
