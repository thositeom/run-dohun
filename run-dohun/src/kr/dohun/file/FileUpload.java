package kr.dohun.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUpload {

	/**
	 * UUID(Universally unique identifier)범용 고유 식별자 사용.
	 * @return
	 */
	public static String getMakeFileName() throws Exception{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 폴더생성
	 * @param path
	 * @param folderName
	 * @return
	 */
	public static void setMakeFolder(String path, String folderName) throws Exception{
		File serverFile = new File(path + folderName + File.separator);
		if(!serverFile.exists()){//폴더가 존재하지 않을때
			serverFile.mkdirs();	
		}
		
	}
	
	/**
	 * 폴더삭제
	 * @param path
	 * @param folderName
	 * @param saveFileName
	 * @return
	 */
	public static void setRemoveFolder(String path, String folderName) throws Exception{
		File serverFile = new File(path + folderName);
	}
	
	/**
	 * 폴더이동
	 * @param fromPath
	 * @param toPath
	 * @param saveFileName
	 * @throws Exception
	 */
	public static void setMoveFolder(String fromPath, String toPath, String saveFileName) throws Exception{
		File file = new File(fromPath+saveFileName);
		File fileToMove = new File(toPath+saveFileName);
		
		if(file.renameTo(fileToMove)){//이동시 true리턴
			file.delete();
		}else{
			System.out.println("::::fileMove Fail");
		}
		
	}
	
	/**
	 * @param request
	 * @param path
	 * @param folderName
	 * @return
	 * @throws Exception
	 */
	public static List<HashMap<String,String>> getFileUpload(HttpServletRequest request, String path, String folderName) throws Exception{
		List<HashMap<String,String>> fileList = new ArrayList<HashMap<String,String>>(); 
		MultipartFile mfile = null;
		String fieldName = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		
		Iterator iter = multipartRequest.getFileNames();
		while (iter.hasNext()) {
			HashMap<String, String> map = new HashMap<String,String>();
			
			fieldName = (String) iter.next(); // 내용을 가져와서
			mfile = multipartRequest.getFile(fieldName);
			
			if(mfile.isEmpty()){
			}
			
			//1.오리지날 파일명
			String origName = new String(mfile.getOriginalFilename().getBytes("utf-8"), "utf-8"); 
			if ("".equals(origName)) {// 파일명이 없다면
				continue;
			}
			//2.파일사이즈
			long fileSize = mfile.getSize();
			//3.확장자
			String extention = origName.substring(origName.lastIndexOf('.')+1);
			//4.저장할 파일명 변환: UUID(Universally unique identifier)범용 고유 식별자 사용하여 파일명 변경. 
			String saveFileName = FileUpload.getMakeFileName() + extention;
			
			//폴성 생성
			setMakeFolder(path, folderName);
			
			if(mfile != null){//생성 및 파일저장
				mfile.transferTo(new File(path + folderName + File.separator + saveFileName));
			}
			
			map.put("origName", origName);
			map.put("fileSize", Long.toString(fileSize));
			map.put("extention", extention);
			map.put("saveFileName", saveFileName);
			
			fileList.add(map);
		}
		return fileList;
	}
	
}
