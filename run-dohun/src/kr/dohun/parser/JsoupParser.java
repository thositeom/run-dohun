package kr.dohun.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupParser {

	public static void main(String[] args) {
		
		String url = "http://bis.gumi.go.kr/moMap/mBusStopResult.do?station_id=188&route_id=&searchType=S&searchKeyword=%EC%9B%90%ED%98%B8&serivce_id=&searchType=S";
		Document doc;
		try {
			
			doc = Jsoup.connect(url).get();
			String title = doc.title();
			
			doc.getElementsByClass("con_view01");
			/*System.out.println("::::::::::::"+ doc.getElementsByClass("con_view01"));
			System.out.println("::::::::::::"+ doc.getElementsByClass("con_view02"));
			System.out.println("::::::::::::"+ doc.getElementsByClass("con_view03"));
			System.out.println("::::::::::::"+ title);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	
}
