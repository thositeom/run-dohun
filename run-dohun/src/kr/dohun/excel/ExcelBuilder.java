package kr.dohun.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
	 *  List로 엑셀에 출력 할 헤더값, 내용 받아와서 엑셀에 뿌리기.
	 */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	
    	String fileName = "게시물 리스트.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
    	
    	// create a new Excel workbook
//		HSSFWorkbook workbook = new HSSFWorkbook();
		
    	// get data model which is passed by the Spring container
    	String[] excelHeaderList = (String[]) model.get("excelHeaderList");
        List<Object> excelList = (List<Object>) model.get("excelList");
//    	List<HashMap> boardHashMap = (List<HashMap>) model.get("boardHashMap");
        
        
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
        
    }
 
}
