package com.son.app.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.son.app.file.service.FileRequest;
import com.son.app.file.service.FileResponse;

@Mapper
public interface FileMapper {
	/**
     * 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    void saveAll(List<FileRequest> files);
    
    /**
     * 파일 리스트 조회
     * @param numbers - FK 번호 (FK)
     * @return 파일 리스트
     */
    List<FileResponse> findAll(@Param("number") Integer number, @Param("type") String type);
    
    /**
     * 파일 리스트 조회
     * @param attachmentFileNumber - PK 리스트
     * @return 파일 리스트
     */
    List<FileResponse> findAllByAttachmentFileNumber(List<Integer> numbers);
    
    /**
     * 파일 삭제
     * @param numbers - PK 리스트
     */
	void deleteAllByAttachmentFileNumber(List<Integer> numbers);
	
	 /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
	FileResponse findByAttachmentFileNumber(Integer attachmentFileNumber);
}
