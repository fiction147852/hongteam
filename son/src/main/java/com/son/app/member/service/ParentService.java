package com.son.app.member.service;

import java.util.List;

public interface ParentService {
	//전체조회
	public List<StudentVO> ParentInfoList();

    public List<StudentVO> getStudentsByParentNumber(int parentNumber);

    public int getParentNumberByEmail(String email);
}
