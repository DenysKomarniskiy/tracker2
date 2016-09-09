package utils;

import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail extends javax.mail.Authenticator {

	Properties props;
	private String subjectText;
	private String bodyText;
	private String pathFile;
	private String addressFrom;
	private String[] addressTo;
	private String[] addressCc = null;
	
	public String getSubjectText() {
		return subjectText;
	}

	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}

	public String[] getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(String[] addressTo) {
		this.addressTo = addressTo;
	}

	public String[] getAddressCc() {
		return addressCc;
	}

	public void setAddressCc(String[] addressCc) {
		this.addressCc = addressCc;
	}

	
	

	public Mail() {
		props = new Properties();
		props.put("mail.smtp.host", "webmail.isd.dp.ua");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.socketFactory.port", 25);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "webmail.isd.dp.ua");
	}

	public boolean send() throws Exception {

		String subject = getSubjectText();
		Session session = Session.getInstance(props, this);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(getAddressFrom()));
		String[] emailsTo = getAddressTo();
		InternetAddress destsTo[] = new InternetAddress[emailsTo.length];
		for (int i = 0; i < emailsTo.length; i++) {
			destsTo[i] = new InternetAddress(emailsTo[i].trim().toLowerCase());
		}
		message.setRecipients(Message.RecipientType.TO, destsTo);

		if (getAddressCc() != null) {
			String[] emailsCc = getAddressCc();
			InternetAddress destsCc[] = new InternetAddress[emailsCc.length];
			for (int i = 0; i < emailsCc.length; i++) {
				destsCc[i] = new InternetAddress(emailsCc[i].trim().toLowerCase());
			}
			message.setRecipients(Message.RecipientType.CC, destsCc);

		}

		message.setSubject(subject, "UTF-8");
		MimeMultipart multipart = new MimeMultipart("related");

		// first part (the html)
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText = getBodyText();

		messageBodyPart.setContent(htmlText, "text/html");

		// add it
		multipart.addBodyPart(messageBodyPart);

		// second part (the image)
		messageBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource(getPathFile());

		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<image>");
		messageBodyPart.setHeader("Content-Type", "image/png");
		messageBodyPart.setHeader("Content-Transfer-Encoding", "base64");

		// add image to the multipart
		multipart.addBodyPart(messageBodyPart);

		// put everything together
		message.setContent(multipart);
		// System.out.print(htmlText);
		// Send message
		Transport.send(message);

		System.out.println("Sent message successfully....");
		//

		return true;
	}

}
