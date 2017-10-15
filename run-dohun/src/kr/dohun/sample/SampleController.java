package kr.dohun.sample;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		List<String> headerList = new ArrayList<String>();
		
		headerList.add("Title");
		headerList.add("name");
		headerList.add("id");
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		list.add(new SampleVO("Effective Java", "Joshua Bloch", "0321356683"));
		list.add(new SampleVO("Head First Java", "Kathy Sierra & Bert Bates", "0321356684"));
		list.add(new SampleVO("Java Generics and Collections", "Philip Wadler", "0321356685"));
		list.add(new SampleVO("Thinking in Java", "Bruce Eckel", "0596527756"));
		list.add(new SampleVO("Spring in Action", "Craig Walls", "1935182358"));
		
		mv.addObject("headerList", headerList);
		mv.addObject("list", list);
		mv.setViewName("excelView");
		return mv;
	}
	
	//Object KEY, VALUE 확인
	public static void main(String[] args) {
//		public void objectSample(){
			List<SampleVO> list = new ArrayList<SampleVO>();
			list.add(new SampleVO("Effective Java", "Joshua Bloch", "0321356683"));
			list.add(new SampleVO("Head First Java", "Kathy Sierra & Bert Bates", "0321356683"));
			list.add(new SampleVO("Java Generics and Collections", "Philip Wadler", "0321356683"));
			list.add(new SampleVO("Thinking in Java", "Bruce Eckel", "0596527756"));
			list.add(new SampleVO("Spring in Action", "Craig Walls", "1935182358"));
			try {
				
			for(int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				for (Field field : obj.getClass().getDeclaredFields()){
		            field.setAccessible(true);
					System.out.println(field.getName()+" : "+field.get(obj));
		        }
			}
			
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//	}
	
	
	
	
	
	
}
