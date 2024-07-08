package com.son.app.member.service;

import java.util.List;

public interface InstructorService {
	
	InstructorVO getInstructorByUsername(String name);
	
    InstructorVO getInstructorByNumber(Integer instructorNumber);
    
    List<InstructorVO> getAllInstructors();
}
