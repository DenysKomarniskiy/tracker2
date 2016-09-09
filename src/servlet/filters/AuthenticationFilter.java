package servlet.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	public AuthenticationFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;		
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();
		String method = req.getMethod();		
		

		if (uri.contains("loginpage") || uri.contains(".") || method.equals("POST")) {

			chain.doFilter(request, response);

		} else if (session == null) {

			res.sendRedirect("/tracker2/loginpage");

		} else if (session.getAttribute("user") == null) {

			res.sendRedirect("/tracker2/loginpage");

		} else {

			chain.doFilter(request, response);

		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

		System.out.println("init AuthenticationFilter");

	}

}
