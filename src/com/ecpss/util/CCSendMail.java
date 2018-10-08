
package com.ecpss.util;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



public class CCSendMail {
	/**
	 * 邮箱发送信息
	 */
	public static boolean setSendMail(String to_mail_address, String setText,String send_mail_address) {
		//System.out.println("send_mail_address-------------"+send_mail_address);
		boolean flag=false;
		try {
			send_mail_address = "xingbill@xingbill.com";
			//System.out.println(send_mail_address+"系统邮箱");
			//send_mail_address = "ecpss@ecpss.cc";
			String password = "Shanghai1607"; // 发行人邮箱密码
			String smtp_server = "smtp.exmail.qq.com";//mx.ym.163.com
			String from_mail_address = send_mail_address;
			Authenticator auth = new PopupAuthenticator1(send_mail_address, password);
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.ssl.enable", "true");
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("username", send_mail_address);
			mailProps.put("password", password);
			mailProps.put("mail.smtp.host", "smtp.exmail.qq.com");
			mailProps.put("mail.smtp.port", 465);
			//Session mailSession = Session.getDefaultInstance(mailProps, auth);
			Session mailSession = Session.getInstance(mailProps, auth);  
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from_mail_address));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));
			message.setSubject("Information from www.xingbill.com(The system e-mail,Please do not reply)");
			MimeMultipart multi = new MimeMultipart("related");
			BodyPart textBodyPart = new MimeBodyPart();
			message.setDataHandler(new javax.activation.DataHandler(
					new StringDataSource(setText, "text/html")));
			textBodyPart.setText(setText);
			multi.addBodyPart(textBodyPart);
			message.setContent(multi);
			message.saveChanges();
			Transport.send(message);
			flag = true;
		} catch (Exception ex) {
			System.err.println("邮件发送失败的原因是：" + ex.getMessage());
			System.err.println("具体错误原因：");
			ex.printStackTrace(System.err);
		}
		return flag;
	}
}

class PopupAuthenticator1 extends Authenticator {
	private String username;
	private String password;

	public PopupAuthenticator1(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}
}
