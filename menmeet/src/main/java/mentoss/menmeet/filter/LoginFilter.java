package mentoss.menmeet.filter;

import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.session.MenMeetSessionCont;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
	//로그인 요구에서 제외할 URI
	private static final String[] checkRequestList = {
			"/myPage**",
			"/mentoringPost/createPost**",
			"/mentoringPost/updatePost**",
			"/mentoringPost/deletePost**",
			"/mentoringPost/showPost**"
	};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		try {
			if (isLoginCheckPath(requestURI)) {//화이트리스트의 uri와 다른 uri면
				log.info("로그인 인증 필터 동작{}", requestURI);
				HttpSession session = httpServletRequest.getSession(false);
				if (session == null || session.getAttribute(MenMeetSessionCont.LOGIN_SESSION) == null) {

					log.info("미인증 사용자, 요청 거부  {}", requestURI);
					httpServletResponse.setContentType("text/html; charset=UTF-8");
					httpServletResponse.getWriter().print("<script>alert('로그인 후 이용해주세요.'); history.back();</script>");
					return;
				}//end of if session == null
				log.info("로그인 인증완료 요청 동작 {}", requestURI);
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e;
		} finally {
			log.info("인증 필터 종료 {}", requestURI);
		}
	}

	private boolean isLoginCheckPath(String requestURI) {
		return PatternMatchUtils.simpleMatch(checkRequestList, requestURI);
	}

}//end of class
