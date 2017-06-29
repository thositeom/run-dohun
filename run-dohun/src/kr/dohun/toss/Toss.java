package kr.dohun.toss;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

@Controller
public class Toss {

	@RequestMapping(value = "/toss.do")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		
		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		try {
		url = new URL("https://toss.im/tosspay/api/v1/payments");
		connection = url.openConnection();
		connection.addRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);
		connection.setDoInput(true);

		JSONObject jsonBody = new JSONObject();
		jsonBody.put("orderNo", "2015072012211");
		jsonBody.put("amount", 10000);
		jsonBody.put("productDesc", "토스티셔츠");
		jsonBody.put("apiKey", "sk_test_apikey1234567890a");
		jsonBody.put("autoExecute", false);
		jsonBody.put("retUrl", "http://YOUR-SITE.COM/ORDER-CHECK");

		BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
		bos.write(jsonBody.toString().getBytes());
		bos.flush();
		bos.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
		responseBody.append(line);
		}
		br.close();
		} catch (Exception e) {
		responseBody.append(e);
		}
		System.out.println(responseBody.toString());
		
		
		mv.setViewName("main/main");
		return mv;
	} 
	
}
