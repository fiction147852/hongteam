package com.son.app.task.mapper;

import java.util.List;

import com.son.app.task.service.TaskVO;

public interface TaskMapper {
	// 전체
	public List<TaskVO> selectTaskAll();
	
	// 단건
	public TaskVO selectTaskInfo(TaskVO taskVO);
	
	// 등록
	public int insertTaskInfo(TaskVO taskVO);
	
	// 수정
	public int updateTaskInfo(TaskVO taskVO);
	
	// 삭제
	public int deleteTaskInfo(int taskNo);
}
