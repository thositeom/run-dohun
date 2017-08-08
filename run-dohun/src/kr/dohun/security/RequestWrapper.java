package kr.dohun.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper{

	public RequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public String[] getParameterValues(String parameter) {
		
		String[] values = super.getParameterValues(parameter);
		
		if(values==null){
			return null;
		}
		int count = values.length;
		String[] encodeValues = new String[count];
		for(int i = 0; i < count; i++){
			encodeValues[i] = cleanXSS(values[i]);
		}
		return encodeValues;
	}
	
	public String getParameter(String parameter){
		String value = super.getParameter(parameter);
		if(value == null){
			return null;
		}
		return cleanXSS(value);
	}
	
	public String getHeader(String name){
		String value = super.getHeader(name);
		if(value == null){
			return null;
		}
		return cleanXSS(value);
	}
	
	private String cleanXSS(String value){
		/*value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");*/
		
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
		value = value.replaceAll("[\\\"\\'][\\s]*vbscript:(.*)[\\\"\\']", "\"\"");
		value = value.replaceAll("document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
		value = value.replaceAll("<script", "&lt;script");
		value = value.replaceAll("script>", "script&gt;");
		value = value.replaceAll("<iframe", "&lt;iframe");
		value = value.replaceAll("<object", "&lt;object");
		value = value.replaceAll("<embed", "&lt;embed"); 
		value = value.replaceAll("onload", "no_onload");
		value = value.replaceAll("expression", "no_expression");
		value = value.replaceAll("onmouseover", "no_onmouseover");
		value = value.replaceAll("onmouseout", "no_onmouseout");
		value = value.replaceAll("onclick", "no_onclick");
		
		return value;
	}

}

	
	
