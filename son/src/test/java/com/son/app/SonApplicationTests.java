package com.son.app;

import com.son.app.attendance.service.StudentAttendanceService;
import com.son.app.attendance.service.StudentLectureInfoVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SonApplicationTests {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	StudentAttendanceService studentAttendanceService;
	
	@Test
	public void pwdEncode() {
		String pw = "1111";
		
		System.out.println(passwordEncoder.encode(pw));
	}

	@Test
	@DisplayName("DH) 특정 학생의 강의에 대한 상세 조회")
	public void lectureList() {
		List<StudentLectureInfoVO> studentLectureInfoVOS = studentAttendanceService.lectureList(1);

		System.out.println(studentLectureInfoVOS);
	}


}
