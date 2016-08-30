package utils;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;


public class HeaderHandlerResolver implements HandlerResolver {

	String login;
	String passw;

	public HeaderHandlerResolver(String login, String passw) {

		this.login = login;
		this.passw = passw;

	}

	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();

		HeaderHandler hh = new HeaderHandler(this.login, this.passw);

		handlerChain.add(hh);

		return handlerChain;
	}
}