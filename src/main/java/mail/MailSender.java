package mail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DAO.MailDAO;
import DAO.impl.MailDAOImpl;

public class MailSender {
	
//	static Properties mailServerProperties;
//	static Session getMailSession;
//	static MimeMessage generateMailMessage;
	
	public void sendMail(float rate) {

		Properties mailServerProperties = System.getProperties();
		Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		MimeMessage generateMailMessage = new MimeMessage(getMailSession);
		
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		MailDAO mailDAO = new MailDAOImpl();
		List<MailEntity> recipientList = new ArrayList<MailEntity>();
		
		try {
			
			recipientList = mailDAO.getMail();
			for (MailEntity recipient : recipientList){
				generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient.getEmail()));
			}
			
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("workfusiontest@mail.ru"));
			generateMailMessage.setSubject("Rate BYR/CRC has change");
			String emailBody = "Rate BYR/CRC has changed. <br><br> new rate = " + rate  
					+ ". <br><br> Regards, <br>Kurmanov Ivan";
			generateMailMessage.setContent(emailBody, "text/html");
			generateMailMessage.setFrom(new InternetAddress("workfusiontest@mail.ru"));
 
			Transport transport = getMailSession.getTransport("smtp");
 
			transport.connect("smtp.mail.ru", "workfusiontest@mail.ru", "12345678qq");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
