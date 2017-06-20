package kr.dohun.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JavaScript {
	
	private List<String> scripts = new ArrayList<String>();
	
	public JavaScript() {
		
	}
	
	public JavaScript(String script) {
		addScript(script);
	}
	
	public void addScript(String script){
		scripts.add(script);
	}
	
	public void execute(HttpServletResponse response, HttpServletRequest request){
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding(request.getCharacterEncoding());
	        PrintWriter out = response.getWriter();
			out.println("<script typre=\"text/javascript\">");
			for(String script : scripts){
				out.println(script);
			}
	        out.println("</script>");
		    out.flush();
		} catch (IOException e) {
		}
	}
	
	public static JavaScript alert(String messages){
		return new JavaScript("alert('" + messages + "'); history.back();");
	}
	
	public static JavaScript alertToUrl(String messages, String url){
		return new JavaScript("alert('" + messages + "'); location.href= '"+ url +"' ");
	}
}
