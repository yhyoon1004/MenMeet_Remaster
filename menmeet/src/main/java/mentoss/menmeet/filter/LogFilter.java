package mentoss.menmeet.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("log filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("log filter doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		StringBuffer requestURL = httpRequest.getRequestURL();

		try {
			log.info("REQUEST URL [{}]", requestURL);
			chain.doFilter(request, response);
		}catch (Exception e){
			throw e;
		}finally{

		}
	}

	@Override
	public void destroy() {
		log.info("log filter destroy");
	}
}
