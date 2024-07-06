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
	
	// 단건
	public TaskVO selectTaskInfo(@Param("taskNumber") Integer taskNumber);
	
	// 등록
	public int insertTaskInfo(TaskVO taskVO);
	
	// 수정
	public int updateTaskInfo(TaskVO taskVO);
	
	// 삭제
	public int deleteTaskInfo(int taskNo);
}
