package kr.dohun.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import kr.dohun.sample.SampleVO;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelBuilder extends AbstractExcelView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)throws Exception {
        // get data model which is passed by the Spring container
    	List<String> headerList = (List<String>) model.get("headerList");
        List<Object> list = (List<Object>) model.get("list");
         
        /*// create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Sheet 1");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Title");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Id");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Name");
        header.getCell(2).setCellStyle(style);*/
        
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
        
        // create data rows
//        int rowCount = 1;
//        for (Object sp : list) {
//            HSSFRow aRow = sheet.createRow(rowCount++);
//            aRow.createCell(0).setCellValue(sp.toString());
//            aRow.createCell(1).setCellValue(sp.hashCode());
//            aRow.createCell(2).setCellValue(sp.hashCode());
//        }
        List listt = new ArrayList();
        for(int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			Map map = new HashMap();
			for (Field field : obj.getClass().getDeclaredFields()){
	            field.setAccessible(true);
				System.out.println(field.getName()+" : "+field.get(obj));
				map.put(field.getName(), field.get(obj));
	        }
			listt.add(map);
		}
       
        
    }
 
}
