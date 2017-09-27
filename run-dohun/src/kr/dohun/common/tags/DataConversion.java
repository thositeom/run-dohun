package kr.dohun.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class DataConversion extends BodyTagSupport{

	private static final long serialVersionUID = -2107364724670885789L;
	private String value;
	
	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
	/*		EVAL_BODY_INCLUDE 태그안 내용 포함 
			SKIP_BODY		  태그안 내용 불포함
			EVAL_PAGE		 JSP페이지에서 끝태그 이후  나머지 코드실행
			bytes를 용량 계산해서 반환
	*/
		try {
			StringBuilder sb = new StringBuilder();
		
			long bytes = Long.parseLong(value);
			boolean si = true; //International System of Units(SI)
			
			int unit = si ? 1000 : 1024; //SI단위로 할지 Data단위로 계산할지 여부
		    if (bytes < unit) {
		    	sb.append(bytes + " B");
		    }else{
		    	int exp = (int) (Math.log(bytes) / Math.log(unit));//Math.log 밑이 2인 로그함수
			    String pre = (si ? "KMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
			    String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
				sb.append(String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre));
		    }
			
			JspWriter out = pageContext.getOut();
			out.println(sb.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}

