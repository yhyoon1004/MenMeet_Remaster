package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.login.LoginFormDTO;
import mentoss.menmeet.DTO.login.LoginStateDTO;
import mentoss.menmeet.domain.User;
import mentoss.menmeet.repository.UserRepository;
import mentoss.menmeet.session.SessionManager;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	private final UserRepository userRepository;
	private final SessionManager sessionManager;
	private final HttpServletResponse httpServletResponse;
	@Override
	public LoginStateDTO confirmLogin(LoginFormDTO targetUser) {
		LoginStateDTO lsDTO = new LoginStateDTO();
		log.info("confirmLogin (targetUser) Id = {} , password = {}",targetUser.getUserId(),targetUser.getUserPassword());
		Optional<User> userById = userRepository.findUserById(targetUser.getUserId());

		if(!userById.isPresent()){//DB에 해당 ID의 유저가 없으면
			lsDTO.setIsLoginConfirmed(false);//로그인 실패
			return lsDTO;
		}else{                      //DB에  해당 ID의 유저가 있으면
			User searchedUser = userById.get();// 해당 유저의 튜플을 가져옴

			if (searchedUser.getUserPassword()// 가져온 유저의 비밀번호가
					.equals(targetUser.getUserPassword())){//입력한 유저의 비밀번호와 같은지
				lsDTO.setIsLoginConfirmed(true);//같으면 로그인여부 DTO에 true로 설정
				//세션에 해당 유저의 UUID생성과 id를 등록해주고, 쿠키를 response에 넣어줌
				sessionManager.createUserCookieAndPutSession(searchedUser.getUserId(),/*searchedUser.getUserName(),*/ httpServletResponse);
			}else {//비밀 번호가 일치하지 않으면 실패 리턴
				lsDTO.setIsLoginConfirmed(false);
			}
		}
		log.info("세션에 있는 정보들 {}", sessionManager.getSessionStore());
		return lsDTO;
	}
}
