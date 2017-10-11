package kr.dohun.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupParser {

	public static void main(String[] args) {
		
		String url = "http://bis.gumi.go.kr/moMap/mBusStopResult.do?station_id=678&route_id=&searchType=N&searchKeyword=10678&serivce_id=&searchType=N";
		Document document;
		
		List list = new ArrayList(); 

		try {
			document = Jsoup.connect(url).get();
			Elements infos = document.select(".stops_list01 li");

			for (int i = 0; i < infos.size(); i++) {
				System.out.println(infos.get(i).select(".con_view01").text());
				System.out.println(infos.get(i).select(".con_view02").text());
				System.out.println(infos.get(i).select(".con_view03").text());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
}
