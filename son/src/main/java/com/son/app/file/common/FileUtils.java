package com.son.app.file.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.son.app.file.service.FileRequest;
import com.son.app.file.service.FileResponse;
/*
 	@Bean은 개발자가 컨트롤 할 수 없는 외부 라이브러리를 빈으로 등록할 때 사용
 	@Component는 개발자가 직접 정의한 클래스를 빈으로 등록할 때 사용
 */
@Component
public class FileUtils {
	// uploadPath = 물리적으로 파일을 저장할 위치를 의미
	// Paths.get( )을 이용하면 OS에 상관없이 디렉터리 경로를 구분
//	private final String uploadPath = Paths.get("D:", "Dev", "upload-files").toString();
//	private final String uploadPath = "C:/uploads";
	
	@Value("${upload.path}")
	private String uploadPath;
	
	// 프로젝트 내 업로드 경로 설정
//	private final String uploadPath = Paths.get("C:", "User", "admin", "git", "hongteam", "son", "src", "main", "resources","static", "uploads").toString();
    
	@PostConstruct
    public void init() {
        createUploadDirectory();
    }

    private void createUploadDirectory() {
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create upload directory");
            }
        }
    }

	/**
	 * 다중 파일 업로드
	 * 
	 * @param multipartFiles - 파일 객체 List
	 * @return DB에 저장할 파일 정보 List
	 */
	
	// 스프링은 파일 업로드를 쉽게 처리할 수 있도록 MultipartFile 인터페이스를 제공
	// 사용자가 화면에서 파일을 업로드한 후 폼을 전송하면, MultipartFile 객체에 사용자가 업로드한 파일 정보가 담김
	public List<FileRequest> uploadFiles(final List<MultipartFile> multipartFiles) {
		List<FileRequest> files = new ArrayList<>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				continue;
			}
			files.add(uploadFile(multipartFile));
		}
		return files;
	}

	/**
	 * 단일 파일 업로드
	 * 
	 * @param multipartFile - 파일 객체
	 * @return DB에 저장할 파일 정보
	 */
	
	//단일(1개) 파일을 디스크에 업로드
	//MultipartFile의 isEmpty( )는 파일의 유무를 체크하는 함수 -> 업로드파일이 없을 경우 null을 리턴해서 로직 종료

	public FileRequest uploadFile(final MultipartFile multipartFile) {

		if (multipartFile.isEmpty()) {
			return null;
		}
		
		// 저장할 파일명, 오늘 날짜, 파일 업로드 경로, 업로드할 파일 객체
		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String saveDir = getUploadPath(today);
        String filePath = saveDir + File.separator + saveName;
        File uploadFile = new File(filePath);
		
        System.out.println("Attempting to save file to: " + uploadFile.getAbsolutePath());
		
		// 파일은 uploadPath에 해당되는 경로에 생성
		// MultipartFile의 transferTo()가 정상적으로 실행되면 파일 생성
		
		try {
			multipartFile.transferTo(uploadFile);
			System.out.println("File saved to: " + filePath);
			System.out.println("File successfully saved to: " + uploadFile.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Failed to save file: " + e.getMessage());
	        e.printStackTrace();

			throw new RuntimeException(e);
		}
		
		// 리턴하는 객체는 FileRequest 타입의 객체
		// 해당 메서드가 리턴하는 객체에는 디스크에 생성된 파일 정보가 담김
		
        return FileRequest.builder()
                .originalFileName(multipartFile.getOriginalFilename())
                .saveFileName(saveName)
                .filePath(filePath.replace(uploadPath, ""))                
                .fileSize(multipartFile.getSize())
                .build();
	}
	/**
     * 저장 파일명 생성
     * @param filename 원본 파일명
     * @return 디스크에 저장할 파일명
     */
	
	// uploadFile( )의 변수 saveName에서 호출하는 메서드
	// 변수 uuid에는 32자리의 랜덤 문자열을, extension에는 업로드 한 파일의 확장자를 담아 
	// (랜덤 문자열 + " . " + 파일 확장자)에 해당되는 파일명을 리턴
	// -> 실제로 디스크에 생성되는 파일명
	
    private String generateSaveFilename(final String filename) {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    	String extension = StringUtils.getFilenameExtension(filename);
    	return uuid + "." + extension;
    }
    
    /**
     * 업로드 경로 반환
     * @return 업로드 경로
     */
    
    
    private String getUploadPath() {
    	return makeDirectories(uploadPath);
    }
    
    /**
    * 업로드 경로 반환
    * @param addPath - 추가 경로
    * @return 업로드 경로
    */
    
    // 변수 uploadPath에 해당되는 경로를 리턴 
    // addPath 파라미터가 선언된 getUploadPath( )는 uploadFile( )의 변수 uploadPath에서 호출하는 메서드로, 
    // 당장은 기본 업로드 경로에 오늘 날짜(today)를 연결하는 용도
    
    private String getUploadPath(final String addPath) {
    	String path = uploadPath + File.separator + addPath;
        File directory = new File(path);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create directory: " + path);
            }
        }
        return directory.getPath();
    }
    
    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    // getUploadPath( )에서 호출하는 메서드. 디스크에 경로(path)에 해당되는 디렉터리(폴더)가 없으면, 
    // path에 해당되는 모든 경로에 폴더를 생성
    
    private String makeDirectories(final String path) {
    	File dir = new File(path);
    	if(dir.exists() == false) {
    		dir.mkdirs();
    	}
    	return dir.getPath();
    }
    
    /**
     * 파일 삭제 (from Disk)
     * @param files - 삭제할 파일 정보 List
     */
    public void deleteFiles(final List<FileResponse> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileResponse file : files) {
            String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
            deleteFile(uploadedDate, file.getSaveFileName());
        }
    }
    
    /**
     * 파일 삭제 (from Disk)
     * @param addPath - 추가 경로
     * @param filename - 파일명
     */
    private void deleteFile(final String addPath, final String filename) {
        String filePath = Paths.get(uploadPath, addPath, filename).toString();
        deleteFile(filePath);
    }

    /**
     * 파일 삭제 (from Disk)
     * @param filePath - 파일 경로
     */
    private void deleteFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
    
    /**
     * 다운로드할 첨부파일(리소스) 조회 (as Resource)
     * @param file - 첨부파일 상세정보
     * @return 첨부파일(리소스)
     */
    public Resource readFileAsResource (final FileResponse file) {
    	String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
    	String filename = file.getSaveFileName();
    	Path filePath = Paths.get(uploadPath, uploadedDate, filename);
    	try {
    		Resource resource = new UrlResource(filePath.toUri());
    		if (resource.exists() == false || resource.isFile() == false) {
    			throw new RuntimeException("file not found : " + filePath.toString());
    		}
    		return resource;
    	} catch (MalformedURLException e){ 
    		throw new RuntimeException("file not found : " + filePath.toString());
    	}
    }
}