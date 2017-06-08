package kr.dohun.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mailController {
	
//	public void setMailAddress(HttpServletRequest request ,HttpServletResponse response, mailVO mailVo){
//		
//	}
	
	@RequestMapping(value = "/mail.do")
	public ModelAndView mailSend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mail/mail");

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //smtp메일 서버 설정
		props.put("mail.smtp.socketFactory.port", "465");//smtp 포트설정
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");//smtp 포트설정

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("",""); //보낼사용자 계정 id/pwd
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("")); //from 이메일주소
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(""));//to 이메일주소
			message.setSubject("Testing Subject"); //제목
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");//내용

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		mv.setViewName("mail/mail");
		return mv;
	}
}
