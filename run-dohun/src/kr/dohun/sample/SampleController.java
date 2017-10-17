package kr.dohun.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.GroupShape;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {
	
	@RequestMapping(value = "/sampleForm.do")
	public ModelAndView sampleForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sample/sampleForm");
		return mv;
	}
	
	/**
	 * Naver lucyXss 필터
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/lucyXssTest.do")
	public ModelAndView lucyXssTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		String lucyXss = request.getParameter("lucyXss");
		mv.addObject("lucyXss",lucyXss);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * 데이터변환 bytes -> KB,MB,GB,TB,EB
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dataConversion.do")
	public ModelAndView dataConversion(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String value = request.getParameter("bytes");
		
		long bytes = Long.parseLong(value);
		boolean si = Boolean.valueOf(request.getParameter("si")).booleanValue(); //International System of Units(SI)
		
		int unit = si ? 1000 : 1024; //SI단위로 할지 Data단위로 계산할지 여부
	    if (bytes < unit) {
	    	mv.addObject("dataConversion", bytes + " B");
	    	System.out.println(bytes + " B");
	    }else{
	    	int exp = (int) (Math.log(bytes) / Math.log(unit));//Math.log 밑이 2인 로그함수
		    String pre = (si ? "KMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
		    String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
			System.out.println(String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre));
			mv.addObject("dataConversion", String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre));
	    }
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	@RequestMapping(value = "/excelViewDown.do")
	public ModelAndView excelViewDown(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try{
		String[] headerList = {"Title", "name", "id"};
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		list.add(new SampleVO("Effective Java", "Joshua Bloch", "0321356683"));
		list.add(new SampleVO("Head First Java","",""));
		list.add(new SampleVO("Java Generics and Collections", "Philip Wadler", "0321356685"));
		list.add(new SampleVO("Thinking in Java", "Bruce Eckel", "0596527756"));
		list.add(new SampleVO("Spring in Action", "Craig Walls", "1935182358"));
		
		String fileName = "테스트 파일엑셀.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");	

		
		// 워크북 생성
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Sheet 1");
        sheet.setDefaultColumnWidth(30);
        
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Title");
        header.createCell(1).setCellValue("name");
        header.createCell(2).setCellValue("id");
		
        HSSFRow aRow = sheet.createRow(1);
        aRow.createCell(0).setCellValue("흥흥");
        aRow.createCell(1).setCellValue("이름");
        aRow.createCell(2).setCellValue("아이이디");
		
        mv.addObject("workbookbook",(HSSFWorkbook)workbook);
        ///
		
		
		mv.addObject("excelHeaderList", headerList);
		mv.addObject("excelList", list);
				
		}catch (Exception e) {
		}		
		mv.setViewName("excelView");
		return mv;
	}
	
	//Object KEY, VALUE 확인
	public static void main(String[] args) {
//		public void objectSample(){
		
		try {
		
			List<SampleVO> list = new ArrayList<SampleVO>();
			list.add(new SampleVO("Effective Java", "Joshua Bloch", "0321356683"));
			list.add(new SampleVO("Head First Java", "Kathy Sierra & Bert Bates", "0321356683"));
			list.add(new SampleVO("Java Generics and Collections", "Philip Wadler", "0321356683"));
			list.add(new SampleVO("Thinking in Java", "Bruce Eckel", "0596527756"));
			list.add(new SampleVO("Spring in Action", "Craig Walls", "1935182358"));
			
			
			List<String> headerList = new ArrayList();
			headerList.add("Title");
			headerList.add("name");
			headerList.add("id");
			
			// 워크북 생성
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			// create a new Excel sheet
	        HSSFSheet sheet = workbook.createSheet("Sheet 1");
	        sheet.setDefaultColumnWidth(30);
	         
	        // create header row
	        int count=0;
	        HSSFRow header = sheet.createRow(0);
	        for (String hList : headerList){
	        	header.createCell(count).setCellValue(hList);
	        	count++;
			}
			
	        int rowCount = 1;
	        List<HashMap> objectList = new ArrayList();
	        for(int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				Map map = new HashMap();
				
				Field[] field2 = obj.getClass().getDeclaredFields();
				HSSFRow sheetRow = sheet.createRow(rowCount);
				for (int j = 0; j < obj.getClass().getDeclaredFields().length; j++) {
					field2[j].setAccessible(true);
					sheetRow.createCell(j).setCellValue(field2[j].get(obj).toString());
		            System.out.println(j+ ":" + field2[j].get(obj).toString());
				}
				rowCount++;
				
				/*int aaaa = 0;
				for (Field field : obj.getClass().getDeclaredFields()){
		            field.setAccessible(true);
		            
						System.out.println(field.getName()+" : "+field.get(obj));
						HSSFRow aRow = sheet.createRow(rowCount);
			            aRow.createCell(aaaa).setCellValue(field.get(obj).toString());
			            aRow.createCell(aaaa+1).setCellValue(field.get(obj).toString());
			            aRow.createCell(aaaa+2).setCellValue(field.get(obj).toString());
			            
			            map.put(field.getName(), field.get(obj));
		        }
				rowCount++;
				objectList.add((HashMap) map);
				*/
			}
	       
			
			// 입력된 내용 파일로 쓰기
			File file = new File("D:\\testWrite.xls");
			FileOutputStream fos = null;

			try {
				fos = new FileOutputStream(file);
				workbook.write(fos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (workbook != null)
						workbook.close();
					if (fos != null)
						fos.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
}
