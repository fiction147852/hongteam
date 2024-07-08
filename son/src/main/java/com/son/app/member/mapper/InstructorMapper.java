package com.son.app.member.mapper;

import java.util.List;

import com.son.app.member.service.InstructorVO;

public interface InstructorMapper {
	
	InstructorVO findByUsername(String name);
	
    InstructorVO findByNumber(Integer instructorNumber);
    
    List<InstructorVO> findAll();
}
