package mentoss.menmeet.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.CubicCurve2D;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class SessionManager {
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
	public static final String SESSION_COOKIE_NAME = "mySessionId";

	//세션의 3가지 기능 1. sessionId생성 / 2. 세션 저장소에 값저장 / 3. sessionId로 응답 쿠키를 생성전달.
	//세션 생성
	public void createSession(Object value, HttpServletResponse response){
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, value);

		//쿠키 생성
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		response.addCookie(mySessionCookie);
	}



	public Object getSession(HttpServletRequest request){
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie==null){
			return null;
		}
		return sessionStore.get(sessionCookie.getValue());
//		Cookie[] cookies = request.getCookies();
//		if(cookies==null){
//			return null;
//		}
//		for (Cookie cookie : cookies) {
//			if(cookie.getName().equals(SESSION_COOKIE_NAME))
//				return sessionStore.get(cookie.getValue());
//		}
//		return null;
	}

	public Cookie findCookie(HttpServletRequest request, String cookieName){
		Cookie[] cookies = request.getCookies();
		if(cookies==null)
			return null;
		return Arrays.stream(cookies)
				.filter(cookie -> cookie.getName().equals(cookieName))
				.findAny()
				.orElse(null);
	}


	public void expire(HttpServletRequest request){
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie!=null){
			sessionStore.remove(sessionCookie.getValue());
		}
	}

}
