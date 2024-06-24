package com.son.app.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.task.mapper.TaskMapper;
import com.son.app.task.service.TaskService;
import com.son.app.task.service.TaskVO;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskMapper taskMapper;
	
	@Override
	public List<TaskVO> taskList() {
		return taskMapper.selectTaskAll();
	}

	@Override
	public TaskVO taskInfo(TaskVO taskVO) {
		return taskMapper.selectTaskInfo(taskVO);
	}

	@Override
	public int insertTask(TaskVO taskVO) {
		int result = taskMapper.insertTaskInfo(taskVO);
		
		return result == 1 ? taskVO.getTaskNumber() : -1;
	}

	@Override
	public Map<String, Object> updateTask(TaskVO taskVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = taskMapper.updateTaskInfo(taskVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", taskVO);
		return map;
	}

	@Override
	public int deleteTask(int taskNo) {
		return taskMapper.deleteTaskInfo(taskNo);
	}

}
