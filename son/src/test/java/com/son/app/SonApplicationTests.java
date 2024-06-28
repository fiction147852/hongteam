package com.son.app;

import com.son.app.attendance.service.StudentAttendanceService;
import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleDetailVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	@DisplayName("DH) 특정 학생의 강의 조회")
	public void lectureList() {
		List<StudentLectureInfoVO> studentLectureInfoVOS = studentAttendanceService.lectureList(1);

		System.out.println(studentLectureInfoVOS);
	}

	@Test
	@DisplayName("DH) 특정 학생의 강의에 대한 상세 조회")
	public void scheduleDetail() {
		StudentScheduleDetailVO studentScheduleDetailVO = new StudentScheduleDetailVO();

		String dateString = "24-06-30";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

        try {
			studentScheduleDetailVO.setDeadlineDate(dateFormat.parse(dateString));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        studentScheduleDetailVO.setStudentNumber(1);

		List<StudentScheduleDetailVO> studentScheduleDetailVOS = studentAttendanceService.scheduleDetail(studentScheduleDetailVO);
		System.out.println(studentScheduleDetailVOS);
	}


}
