package com.son.app.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.son.app.file.service.FileRequest;

@Mapper
public interface FileMapper {
	/**
     * 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    void saveAll(List<FileRequest> files);
}
