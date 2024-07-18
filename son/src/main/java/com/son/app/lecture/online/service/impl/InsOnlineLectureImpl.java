package com.son.app.lecture.online.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.online.mapper.InsOnlineLectureMapper;
import com.son.app.lecture.online.service.InsOnlineLectureService;
import com.son.app.lecture.online.service.OnlineLectureVO;
import com.son.app.page.PageVO;

@Service
public class InsOnlineLectureImpl implements InsOnlineLectureService{
	
	@Autowired
	InsOnlineLectureMapper onlineLectureMapper;

	@Override
	public List<OnlineLectureVO> onlineLectureList(Integer lectureNumber, PageVO pageVO) {
		int start = Math.max(0, (pageVO.getPage() -1 ) * pageVO.getPageSize());
		int end = start + pageVO.getPageSize();
		return onlineLectureMapper.selectOnlineLectureAll(lectureNumber, start, end);
	}
	public PageVO getPageInfo(Integer lectureNumber, int page) {
		int totalItems = onlineLectureMapper.countOnlineLecs(lectureNumber);
		return new PageVO(page, totalItems, 5, 5);
	}

	@Override
	public OnlineLectureVO onlineLecsInfo(Integer onlineLectureNumber) {
		return onlineLectureMapper.selectLectureInfo(onlineLectureNumber);
	}
}
