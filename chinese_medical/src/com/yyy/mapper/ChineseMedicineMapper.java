package com.yyy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyy.model.ChineseMedicine;

public interface ChineseMedicineMapper {
	
	public List<ChineseMedicine> initChineseMedicine();
	public List<ChineseMedicine> chineseMedicinePage(@Param("start") int start,@Param("end") int end);
	public int cmPageMaxNum();
	public ChineseMedicine getCMDetails(int mid);
}
