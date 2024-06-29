package com.son.app.lecture.online.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.online.mapper.InsOnlineLectureMapper;
import com.son.app.lecture.online.service.InsOnlineLectureService;
import com.son.app.lecture.online.service.OnlineLectureVO;

@Service
public class InsOnlineLectureImpl implements InsOnlineLectureService{
	
	@Autowired
	InsOnlineLectureMapper onlineLectureMapper;

	@Override
	public List<OnlineLectureVO> onlineLectureList() {
		return onlineLectureMapper.selectOnlineLectureAll();
	}

	@Override
	public OnlineLectureVO onlineLecsInfo(OnlineLectureVO onlineLectureVO) {
		return onlineLectureMapper.selectLectureInfo(onlineLectureVO);
	}

	@Override
	public int insertOnlineLecture(OnlineLectureVO onlineLectureVO) {
		int result = onlineLectureMapper.insertOnlineLectureInfo(onlineLectureVO);
		
		return result == 1 ? onlineLectureVO.getOnlineLectureNumber() : -1;
	}

	@Override
	public Map<String, Object> updateOnlineLecture(OnlineLectureVO onlineLectureVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = onlineLectureMapper.updateOnlineLectureInfo(onlineLectureVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", onlineLectureVO);
		
		return map;
	}

	@Override
	public int deleteOnlineLecture(int onlineLectureNo) {
		return onlineLectureMapper.deleteOnlineLectureInfo(onlineLectureNo);
	}
}
