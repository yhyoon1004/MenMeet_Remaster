package mentoss.menmeet.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
	//로그인 요구에서 제외할 URI
	private static final String[] whitelist = {"/", "/signup**", "/login", "/userList*"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		try{
			log.info("인증 체크 필터 시작{}", requestURI);
			if(isLoginCheckPath(requestURI)){//화이트리스트의 uri와 다른 uri면
				log.info("인증 체크 로직 실행{}", requestURI);//인증 체크
				HttpSession session = httpServletRequest.getSession(false);
				if(session==null||session.getAttribute("MenMeetSession")==null){
					log.info("미인증 사용자 요청 {}",requestURI);
					httpServletResponse.sendRedirect("https://www.youtube.com");
					return;
				}//end of if session==null
			}
			chain.doFilter(request, response);
		}catch (Exception e){
			throw e;
		}finally {
			log.info("인증 체크 필터 종료 {}", requestURI);
		}
	}
/*
* 화이트 리스트의 경우 인증 체크X
* */

	private boolean isLoginCheckPath(String requestURI){
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
	}

}//end of class
