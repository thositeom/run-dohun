package kr.dohun.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupParser {

	public static void main(String[] args) {
		
		String url = "http://bis.gumi.go.kr/moMap/mBusStopResult.do?station_id=188&route_id=&searchType=S&searchKeyword=%EC%9B%90%ED%98%B8&serivce_id=&searchType=S";
		 Document document;
		try {
			
			document = Jsoup.connect(url).get();
			String title = document.title();
			
			document.getElementsByClass("con_view01").first();
			System.out.println("::::::::::::"+ title);
			
//			System.out.println("::::::::::::"+ document.getElementsByTag("span"));
			
			System.out.println("::::::::::::"+ document.select(".con_view01 span") );
			/*System.out.println("::::::::::::"+ document.getElementsByClass("con_view01"));
			System.out.println("::::::::::::"+ document.getElementsByClass("con_view02"));
			System.out.println("::::::::::::"+ document.getElementsByClass("con_view03"));*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	
}
