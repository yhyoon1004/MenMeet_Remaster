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

@Component
public class SessionManager {
	//세션이름(유저ID[UUID]),
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
	public static final String SESSION_COOKIE_NAME = "menmeetSessionId";//서비스에 사용할 쿠키 이름

	//세션의 3가지 기능 1. sessionId생성 / 2. 세션 저장소에 값저장 / 3. sessionId로 응답 쿠키를 생성전달.


	//세션에 쿠키정보생성하는 메서드  //로그인 했을 때
	public void createUserCookieSession(Object value, HttpServletResponse response){
		//UUID로 랜덤 센션값 생성
		String sessionId = UUID.randomUUID().toString();

		//세션에 해당 유저 값 저장
		sessionStore.put(sessionId, value);

		//세션에 생성된 식별자 UUID로 쿠키 생성
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);

		//응답정보에 생성한 쿠키를 넣어줌
		response.addCookie(mySessionCookie);
	}


//세션에 해당 쿠키 정보를 찾아서 반환하는 메서드
	public Object getSession(HttpServletRequest request){
		//브라우저의 쿠키들중 세션에 관련한 쿠키를 받아옴
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		//세션 관련 쿠키가 없으면 null반환
		if(sessionCookie==null) return null;
		//있으면 세션저장소에 해당 쿠키를 조회해서 반환
		return sessionStore.get(sessionCookie.getValue());
	}

	//브라우저가 보내온 쿠키들중 로그인에 사용하는 쿠키 정보를 받아옴
	public Cookie findCookie(HttpServletRequest request, String cookieName){
		//브라우저가 보낸 리퀘스트에 쿠키들을 받아서 쿠키 배열에 저장
		Cookie[] cookies = request.getCookies();
		//만약 보낸 쿠키가 없으면 null반환
		if(cookies==null)
			return null;
		//있으면 쿠키배열에 값을 뒤져서 해당 쿠키이름과 같은 쿠키가 있으면 해당 쿠키를 넘겨줌 없으면 null을 넘김
		return Arrays.stream(cookies)
				.filter(cookie -> cookie.getName().equals(cookieName))
				.findAny()
				.orElse(null);
	}

//세션에 해당 쿠키를 만기시키는 메서드
	public void expire(HttpServletRequest request){
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie!=null){
			sessionStore.remove(sessionCookie.getValue());
		}
	}

}
