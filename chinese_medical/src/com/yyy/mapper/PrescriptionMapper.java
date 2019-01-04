package com.yyy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyy.model.Prescription;

public interface PrescriptionMapper {
	
	public List<Prescription> initPrescription();
	public List<Prescription> prescriptionPage(@Param("start") int start,@Param("end") int end);
	public int prePageMaxNum();
	public Prescription getPreDetails(int pid);
}
