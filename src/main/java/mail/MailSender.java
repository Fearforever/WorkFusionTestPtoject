package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	public static void main (String[] args){
		MailSender ms = new MailSender();
		ms.sendMail(123f);
	}
	
	public void sendMail(float rate) {

		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
		try {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("Fearforever@mail.ru"));
			generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("Fearforever@tut.by"));
			generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("workfusiontest@mail.ru"));
			generateMailMessage.setSubject("Rate BYR/CRC change");
			String emailBody = "Rate BYR/CRC canged. <br><br> new rate = " + rate  
					+ ". <br><br> Regards, <br>Kurmanov Ivan";
			generateMailMessage.setContent(emailBody, "text/html");
			generateMailMessage.setFrom(new InternetAddress("workfusiontest@mail.ru"));
 
			Transport transport = getMailSession.getTransport("smtp");
 
			transport.connect("smtp.mail.ru", "workfusiontest@mail.ru", "12345678q");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
