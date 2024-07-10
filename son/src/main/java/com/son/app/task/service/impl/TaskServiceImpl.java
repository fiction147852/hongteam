package com.son.app.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.page.PageVO;
import com.son.app.task.mapper.TaskMapper;
import com.son.app.task.service.TaskService;
import com.son.app.task.service.TaskVO;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskMapper taskMapper;
	
    public List<TaskVO> taskList(Integer lectureNumber, PageVO pageVO) {
        int start = Math.max(0, (pageVO.getPage() - 1) * pageVO.getPageSize());
        int end = start + pageVO.getPageSize();
        return taskMapper.selectTaskAll(lectureNumber, start, end);
    }

    public PageVO getPageInfo(Integer lectureNumber, int page) {
        int totalItems = taskMapper.countTasks(lectureNumber);
        return new PageVO(page, totalItems, 5, 5); // 10: pageSize, 5: pageGroupSize
    }

	@Override
    public TaskVO taskInfo(Integer taskNumber) {
        return taskMapper.selectTaskInfo(taskNumber);
    }

	@Override
	public int insertTask(TaskVO taskVO) {
		int result = taskMapper.insertTaskInfo(taskVO);
		
		return result == 1 ? taskVO.getTaskNumber() : -1;
	}

	@Override
	public Map<String, Object> updateTask(TaskVO taskVO) {
	    Map<String, Object> map = new HashMap<>();
	    boolean isSucceeded = false;

	    TaskVO existingTask = taskMapper.selectTaskInfo(taskVO.getTaskNumber());
	    if (existingTask != null && existingTask.getLectureNumber() == taskVO.getLectureNumber()) {
	        int result = taskMapper.updateTaskInfo(taskVO);
	        if (result == 1) {
	            isSucceeded = true;
	        }
	    }

	    map.put("result", isSucceeded);
	    map.put("target", taskVO);
	    return map;
	}

	@Override
	public boolean deleteTask(Integer taskNo) {
	    return taskMapper.deleteTaskInfo(taskNo) > 0;
	}

}
