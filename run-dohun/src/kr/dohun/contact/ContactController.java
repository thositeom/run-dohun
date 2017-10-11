package kr.dohun.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	
	@RequestMapping(value = "/contactForm.do")
	public ModelAndView honeyFrom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		try {
			String url = "http://bis.gumi.go.kr/moMap/mBusStopResult.do?station_id=678&route_id=&searchType=N&searchKeyword=10678&serivce_id=&searchType=N";
			Document document;
			List list = new ArrayList(); 
			
			document = Jsoup.connect(url).get();
			Elements infos = document.select(".stops_list01 li");
			
			for (int i = 0; i < infos.size(); i++) {
				System.out.println(infos.get(i).select(".con_view01").text());
				System.out.println(infos.get(i).select(".con_view02").text());
				System.out.println(infos.get(i).select(".con_view03").text());
				
				Map map = new HashMap();
				map.put("info01", infos.get(i).select(".con_view01").text());
				map.put("info02", infos.get(i).select(".con_view02").text());
				map.put("info03", infos.get(i).select(".con_view03").text());
				
				list.add(map);
			}
			
			mv.addObject("resultParserList", list);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("contact/contactForm");
		return mv;
	}
}
