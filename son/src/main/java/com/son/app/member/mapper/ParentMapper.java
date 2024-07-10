package com.son.app.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.member.service.ParentVO;
import com.son.app.member.service.StudentVO;

public interface ParentMapper  {

	//상담일정 전체조회
	public List<StudentVO> childInfoAll(int parentNumber);

	public List<StudentVO> getStudentsByParentNumber(@Param("parentNumber")int parentNumber);

	public int getParentNumberByEmail(@Param("email") String email);

	public List<ParentVO> mypageInfo(int parentNumber);

	public int saves(ParentVO parent);
}
