package com.son.app.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.son.app.page.PageVO;
import com.son.app.task.mapper.TaskMapper;
import com.son.app.task.service.TaskService;
import com.son.app.task.service.TaskVO;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskMapper taskMapper;
	
    @Override
    public List<TaskVO> taskList(Integer lectureNumber, PageVO pageVO) {
        int start = Math.max(0, (pageVO.getPage() - 1) * pageVO.getPageSize());
        int end = start + pageVO.getPageSize();
        List<TaskVO> tasks = taskMapper.selectTaskAll(lectureNumber, start, end);
        return tasks;
    }

    public PageVO getPageInfo(Integer lectureNumber, int page) {
        int totalItems = taskMapper.countTasks(lectureNumber);
        return new PageVO(page, totalItems, 5, 5);
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
    @Transactional
    public void insertTaskWithSubmissions(TaskVO taskVO) {
        // 과제 정보 삽입
        int taskNumber = insertTask(taskVO);
        
        if (taskNumber > 0) {
            // 해당 강의의 학생 목록 가져오기
            List<Integer> studentNumbers = taskMapper.getStudentNumbersByLecture(taskVO.getLectureNumber());
            
            // 각 학생에 대해 과제 제출 정보 생성
            for (Integer studentNumber : studentNumbers) {
                TaskVO submitTaskVO = new TaskVO();
                submitTaskVO.setTaskNumber(taskNumber);
                submitTaskVO.setStudentNumber(studentNumber);
                submitTaskVO.setTaskSubmitStatus("미제출");
                submitTaskVO.setFeedbackStatus("피드백 미완료");
                taskMapper.insertTaskSubmit(submitTaskVO);
            }
        }
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

    @Override
    public List<TaskVO> getSubmittedStudentsList(Integer taskNumber, Integer lectureNumber, PageVO pageVO, String searchKeyword) {
        int start = Math.max(0, (pageVO.getPage() - 1) * pageVO.getPageSize());
        int end = start + pageVO.getPageSize();
        return taskMapper.selectSubmittedStudents(taskNumber, lectureNumber, start, end, searchKeyword);
    }

    @Override
    public PageVO getSubmittedStudentPageInfo(Integer taskNumber, int page, String searchKeyword) {
        int totalItems = taskMapper.countSubmittedStudents(taskNumber, searchKeyword);
        return new PageVO(page, totalItems, 10, 5);
    }
	
    @Override
    public List<TaskVO> searchTasks(Integer lectureNumber, String searchKeyword, PageVO pageVO) {
        int start = Math.max(0, (pageVO.getPage() - 1) * pageVO.getPageSize());
        int end = start + pageVO.getPageSize();
        return taskMapper.searchTasks(lectureNumber, searchKeyword, start, end);
    }
    
    @Override
    public PageVO getSearchPageInfo(Integer lectureNumber, String searchKeyword, int page) {
        int totalItems = taskMapper.countSearchTasks(lectureNumber, searchKeyword);
        return new PageVO(page, totalItems, 5, 5);
    }


}
