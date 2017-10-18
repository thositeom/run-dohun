package kr.dohun.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelBuilder extends AbstractExcelView {
	
	/**
	 * 	파일명 : ExcelView호출 컨트롤러에서 response.setHeader 사용하여 지정
	 *  Excel Header: excelHeaderList
	 *  Excel Contents: 헤더값을 키값으로 가져옴 -> List<HashMap<헤더값, value>>
	 */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	/*
    	 * ExcelView 작성하는 컨트롤러에서 타이틀명 지정해서 보내기 
    	String fileName = "게시물 리스트.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
    	*/
    	
    	// create a new Excel workbook
//		HSSFWorkbook workbook = new HSSFWorkbook();
		
    	// get data model which is passed by the Spring container
    	String[] excelHeaderList = (String[]) model.get("excelHeaderList");
    	List<HashMap> excelList = (List<HashMap>) model.get("excelHashMap");
//    	List<Object> excelList = (List<Object>) model.get("excelList");
        
        
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Sheet 1");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("맑은 고딕");
        style.setFont(font);

        // create header row
        int count=0;
        HSSFRow header = sheet.createRow(0);
        for (String hList : excelHeaderList){
        	header.createCell(count).setCellValue(hList);
        	header.getCell(count).setCellStyle(style);
        	count++;
		}
        
        // create contents row
        for (int i = 0; i < excelList.size(); i++) {
        	HSSFRow sheetRow = sheet.createRow(i+1);
        	for (int j = 0; j < excelHeaderList.length; j++) {
        		if(excelList.get(i).get(excelHeaderList[j]) == null){
        			sheetRow.createCell(j).setCellValue("");
        		}else{
        			sheetRow.createCell(j).setCellValue(excelList.get(i).get(excelHeaderList[j]).toString());
        		}
			}
		}
        
        /* object값가져와 excel row 생성
        // create contents row
        int rowCount = 1;
        List<HashMap> objectList = new ArrayList();
        for(int i = 0; i < excelList.size(); i++) {
			Object obj = excelList.get(i);
			Map map = new HashMap();
			
			Field[] field = obj.getClass().getDeclaredFields();
			HSSFRow sheetRow = sheet.createRow(rowCount);
			for (int j = 0; j < obj.getClass().getDeclaredFields().length; j++) {
				field[j].setAccessible(true);
				
				if(field[j].get(obj) == null){
					sheetRow.createCell(j).setCellValue("");
					System.out.println(field[j].getName()+ ":" + "null");
				}else{
					sheetRow.createCell(j).setCellValue(field[j].get(obj).toString());
					System.out.println(field[j].getName()+ ":" + field[j].get(obj).toString());
				}
				sheetRow.getCell(j).setCellStyle(style);
			}
			rowCount++;
    	}
		*/
    }
}
