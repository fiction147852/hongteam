package com.son.app.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.task.service.TaskVO;

public interface TaskMapper {
	// 전체
	List<TaskVO> selectTaskAll(@Param("lectureNumber") Integer lectureNumber, 
					           @Param("start") int start, 
					           @Param("end") int end);
	int countTasks(@Param("lectureNumber") Integer lectureNumber);
	
    List<TaskVO> selectSubmittedStudents(@Param("taskNumber") Integer taskNumber,
							             @Param("lectureNumber") Integer lectureNumber,
							             @Param("start") int start,
							             @Param("end") int end,
							             @Param("searchKeyword") String searchKeyword);

    int countSubmittedStudents(@Param("taskNumber") Integer taskNumber,
    						   @Param("searchKeyword") String searchKeyword);
    int countTotalStudents(@Param("taskNumber") Integer taskNumber);
    
    // 검색
    List<TaskVO> searchTasks(@Param("lectureNumber") Integer lectureNumber, 
            @Param("searchKeyword") String searchKeyword,
            @Param("start") int start,
            @Param("end") int end);

	int countSearchTasks(@Param("lectureNumber") Integer lectureNumber, 
	        @Param("searchKeyword") String searchKeyword);
	
	// 단건
	public TaskVO selectTaskInfo(@Param("taskNumber") Integer taskNumber);
	
	public TaskVO selectSubmittedInfo(@Param("taskSubmitNumber") Integer taskSubmitNumber);
	
	// 등록
	public int insertTaskInfo(TaskVO taskVO);
    void insertTaskSubmit(TaskVO taskVO);
    List<Integer> getStudentNumbersByLecture(int lectureNumber);
	
	// 수정
	public int updateTaskInfo(TaskVO taskVO);
	
	// 삭제
	public int deleteTaskInfo(int taskNo);
}
