package com.son.app.task.service;

import java.util.List;
import java.util.Map;

public interface TaskService {
	public List<TaskVO> taskList();
	
	public TaskVO taskInfo(TaskVO taskVO);
	
	public int insertTask(TaskVO taskVO);
	
	public Map<String, Object> updateTask(TaskVO taskVO);
	
	public int deleteTask (int taskNo);
}
