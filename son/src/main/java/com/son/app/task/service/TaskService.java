package com.son.app.task.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.son.app.page.PageVO;

public interface TaskService {
    public List<TaskVO> taskList(@Param("lectureNumber") Integer lectureNumber, PageVO pageVO);
    public PageVO getPageInfo(@Param("lectureNumber") Integer lectureNumber, int page);
    
    List<TaskVO> getSubmittedStudentsList(Integer taskNumber,
							              Integer lectureNumber,
							              PageVO pageVO,
							              String searchKeyword);

    PageVO getSubmittedStudentPageInfo(Integer taskNumber, int page, String searchKeyword);
    
    // 검색
    List<TaskVO> searchTasks(Integer lectureNumber, String searchKeyword, PageVO pageVO);
    PageVO getSearchPageInfo(Integer lectureNumber, String searchKeyword, int page);
	
	public TaskVO taskInfo(Integer taskNumber);
	
	public int insertTask(TaskVO taskVO);
    void insertTaskWithSubmissions(TaskVO taskVO);
	
	public Map<String, Object> updateTask(TaskVO taskVO);
	
	public boolean deleteTask(Integer taskNo);
}
