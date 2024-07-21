package com.son.app.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.lecture.service.LectureVO;

public interface InsLectureMapper {
	public List<LectureVO> selectLectureAll();
	
	public LectureVO selectLectureInfo(@Param("lectureNumber") Integer lectureNumber);
	
	List<Integer> getStudentNumbersByLecture(@Param("lectureNumber") Integer lectureNumber);
	
    List<LectureVO> getStudentInfoByLecture(@Param("lectureNumber") Integer lectureNumber,
								            @Param("searchType") String searchType,
								            @Param("searchKeyword") String searchKeyword,
								            @Param("start") int start,
								            @Param("end") int end);

	int countStudentsByLecture(@Param("lectureNumber") Integer lectureNumber,
							   @Param("searchType") String searchType,
							   @Param("searchKeyword") String searchKeyword);
	
	List<LectureVO> selectLecturesByInstructor(@Param("instructorNumber") int instructorNumber);
}
