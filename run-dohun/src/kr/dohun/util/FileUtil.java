package com.saltware.enface.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.saltware.enview.Enview;

/**
 * 파일 Utility
 * @author smna
 *
 */
public class FileUtil {
	
	private static final Log logger = LogFactory.getLog(FileUtil.class);

	private static String uploadFilePath = Enview.getConfiguration().getString("enface.upload.dir");
	private static String downloadFilePath = Enview.getConfiguration().getString("enface.download.dir");
	
	/**
	 * 업로드 경로를 리턴한다.<br>
	 * 업로드 경로는 enview.properties의 enface.upload.dir 속성으로 지정된다. 
	 * @return 업로드 경로
	 */
	public static String getUploadPath(){
		return uploadFilePath;
	}
	
	/**
	 * 다운로드경로를 리턴한다.<br>
	 * 다운로드 경로는 enview.properties의 enface.download.dir 속성으로 지정된다. 
	 * @return 다운로드경로
	 */
	public static String getDownLoadPath(){
		return downloadFilePath;
	}
	
	/**
	 * 사용자 폴더에 따른 다운로드 경로를 리턴한다. 
	 * @param userFolder
	 * @return 다운로드경로
	 */
	public static String getDownLoadPath(String userFolder){
		return downloadFilePath + userFolder + "/";
	}
	
	
	/**
	 * 업로드 파일을 사용자폴더에 저장한다.
	 * @param userFolder 사용자 폴더
	 * @param multipartFile 업로드파일
	 * @param isUploadInstallFolder  
	 * @return 파일명
	 */
	public static String uploadMultipartFile(String userFolder, MultipartFile multipartFile, boolean isUploadInstallFolder){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "";
		if(multipartFile.getOriginalFilename() != null && !multipartFile.getOriginalFilename().equals("")){
			fileName = sdf.format(date) + "_" + multipartFile.getOriginalFilename();
		}
		else return "";
		File file = null;
		if(isUploadInstallFolder){
			file = new File(Enview.getRealPath("") + uploadFilePath + userFolder + "/" + fileName);
		}
		else file = new File(uploadFilePath + userFolder + "/" + fileName);
		
		logger.debug("FileUtil: uploadFilePath = " + file.getPath());
		logger.debug("FileUtil: multipartFile = " + multipartFile.getOriginalFilename());
		logger.debug("FileUtil: fileSize = " + multipartFile.getSize() + "bytes");
		try {
			multipartFile.transferTo(file);
			logger.debug("FileUtil: file.path = " + file.getPath());
			logger.debug("FileUtil: multipartFile transfer To file, Complete.");
		} catch (IllegalStateException e) {
			logger.error( e.getMessage(), e);
		} catch (IOException e) {
			logger.debug("FileUtil: IOException = " + e);
		}
		return fileName;
	}
}
