package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecUsernameToken;
import org.apache.ws.security.util.XmlSchemaDateFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

	String login;
	String passw;

	public HeaderHandler(String login, String passw) {
		this.login = login;
		this.passw = passw;
	}

	public boolean handleMessage(SOAPMessageContext smc) {

		Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty.booleanValue()) {

			SOAPMessage message = smc.getMessage();
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				message.writeTo(out);
				System.out.println("before insert security::" + new String(out.toByteArray()));

				addSecurityHeader(message);

				out = new ByteArrayOutputStream();
				message.writeTo(out);
				System.out.println("after insert security::" + new String(out.toByteArray()));

			} catch (SOAPException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (WSSecurityException e) {
				e.printStackTrace();
			}

		} 

		return outboundProperty;
	}

	private void addSecurityHeader(SOAPMessage message) throws SOAPException, IOException, ParserConfigurationException, SAXException, WSSecurityException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		message.writeTo(out);
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(in);

		Element timestampElement = document.createElementNS(WSConstants.WSU_NS, WSConstants.WSU_PREFIX + ":" + WSConstants.TIMESTAMP_TOKEN_LN);
		DateFormat zulu = new XmlSchemaDateFormat();
		Element elementCreated = document.createElementNS(WSConstants.WSU_NS, WSConstants.WSU_PREFIX + ":" + WSConstants.CREATED_LN);
		Date createdDate = new Date();
		elementCreated.appendChild(document.createTextNode(zulu.format(createdDate)));
		timestampElement.appendChild(elementCreated);

		WSSecUsernameToken usernametoken = new WSSecUsernameToken();
		usernametoken.setPasswordType(WSConstants.PASSWORD_TEXT);
		usernametoken.setUserInfo(this.login, this.passw);

		WSSecHeader secHeader = new WSSecHeader("", false);
		secHeader.insertSecurityHeader(document);
		usernametoken.build(document, secHeader);
		secHeader.getSecurityHeader().appendChild(timestampElement);

		message.getSOAPPart().setContent(new DOMSource(document));
	}

	public Set getHeaders() {
		// throw new UnsupportedOperationException("Not supported yet.");
		return null;
	}

	public boolean handleFault(SOAPMessageContext context) {
		// throw new UnsupportedOperationException("Not supported yet.");
		return true;
	}

	public void close(MessageContext context) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}
}
