package utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail extends javax.mail.Authenticator {
	
	Properties props;
	
	public Mail (){
		props = new Properties();
		props.put("mail.smtp.host", "smtp.isd.dp.ua");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.socketFactory.port", 25);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.isd.dp.ua");
	}

	public boolean send() throws Exception {
		
		Session session = Session.getDefaultInstance(props, this);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("tqc.autobot@isd.dp.ua"));
		String[] emails = { "deko@isd.dp.ua" };
		InternetAddress dests[] = new InternetAddress[emails.length];
		for (int i = 0; i < emails.length; i++) {
			dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
		}
		message.setRecipients(Message.RecipientType.TO, dests);
		message.setSubject("TEST from Java", "UTF-8");
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setContent("<b>Test body</b>", "text/html;charset=utf-8");
		mp.addBodyPart(mbp);
		message.setContent(mp);
		message.setSentDate(new java.util.Date());
		Transport.send(message);

		return true;
	}

	
}
