package servlet.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/authFilter")
public class authFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Map<String, String> cook =  utils.Utils.getCookiesMap(req.getCookies());
		
		if (req.getMethod() == "GET" && (cook.get("runner") == null || cook.get("testing_id") == null)) {
			res.sendRedirect("/tracker2/loginpage");
			return;
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
