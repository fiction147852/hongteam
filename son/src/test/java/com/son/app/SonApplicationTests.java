package com.son.app;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.attendance.service.StudentAttendanceService;
import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleDetailVO;
import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.StudentExamService;
import com.son.app.lecture.mapper.StudentLectureMapper;
import com.son.app.lecture.service.LectureMaterialDetailVO;
import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.lecture.service.StudentLectureService;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SonApplicationTests {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	StudentAttendanceService studentAttendanceService;

	@Autowired
	StudentLectureService studentLectureService;

	@Autowired
	StudentExamService studentExamService;

	@Autowired
	StudentTaskService studentTaskService;

	@Test
	public void pwdEncode() {
		String pw = "1111";
		
		System.out.println(passwordEncoder.encode(pw));
		System.out.println(studentAttendanceService);
	}

	@Test
	@DisplayName("DH) 특정 학생의 전체 스케줄 조회")
	public void scheduleAllList() {
		studentAttendanceService.scheduleList(1);
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

		String dateString = "24-06-28";
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

	@Test
	@DisplayName("DH) 강의 자료 조회")
	public void lectureMaterialList() {
		List<LectureMaterialVO> lectureMaterialVOS = studentLectureService.lectureMaterialList(1, "1", 0,5);

		System.out.println(lectureMaterialVOS);
	}

	@Test
	@DisplayName("DH) 강의 조회")
	public void lectureInfo() {
		StudentLectureInfoVO studentLectureInfoVO = studentAttendanceService.lectureInfo(1);
		System.out.println(studentLectureInfoVO);
	}

	@Test
	@DisplayName("DH) 강의 자료 상세 페이지 조회")
	public void lectureMaterialInfo() {
		LectureMaterialDetailVO lectureMaterialDetailVO = studentLectureService.lectureMaterialInfo(1);

		System.out.println(lectureMaterialDetailVO);
	}

	@Test
	@DisplayName("DH) 시험 목록 조회")
	public void studentExamList() {
		List<ExamListVO> examListVOList = studentExamService.examList(1, null, "전체", 1, 5);

		System.out.println(examListVOList);
	}

	@Test
	@DisplayName("DH) 시험 상세 정보")
	public void studentExamInfo() {
		List<ExamInfoVO> examInfoVOList = studentExamService.examInfo(1);

		System.out.println(examInfoVOList);
	}

	@Test
	@DisplayName("DH) GRADING_RESULT 테이블에 데이터 삽입")
	public void insertData() {
		GradingResult gradingResult = new GradingResult();
		gradingResult.setParticipateNumber(2);
		gradingResult.setQuestionNumber(13);
		gradingResult.setStudentAnswer("4");


		studentExamService.autoGradeExam(gradingResult);
	}

	@Test
	@DisplayName("DH) 시험 성적 구하기")
	public void examScore() {
		studentExamService.modifyParticipateStatus(2);
	}

	@Test
	@DisplayName("DH) 과제 상서 정보")
	public void taskInfo() {
		TaskListVO taskListVO = studentTaskService.taskInfo(2);

		System.out.println(taskListVO);
	}

}
