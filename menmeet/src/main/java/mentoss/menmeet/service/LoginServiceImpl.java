package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.login.LoginFormDTO;
import mentoss.menmeet.DTO.login.LoginStateDTO;
import mentoss.menmeet.domain.User;
import mentoss.menmeet.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	private final UserRepository userRepository;
	private final HttpServletRequest httpServletRequest;
	private final HttpServletResponse httpServletResponse;

	@Override
	public LoginStateDTO confirmLogin(LoginFormDTO targetUser) {
		LoginStateDTO lsDTO = new LoginStateDTO();
		log.info("confirmLogin (targetUser) Id = {} , password = {}", targetUser.getUserId(), targetUser.getUserPassword());
		Optional<User> userById = userRepository.findUserById(targetUser.getUserId());

		if (!userById.isPresent()) {//DB에 해당 ID의 유저가 없으면
			lsDTO.setIsLoginConfirmed(false);//로그인 실패
			return lsDTO;
		} else {//DB에  해당 ID의 유저가 있으면
			User searchedUser = userById.get();// 해당 유저의 튜플을 가져옴
			log.info("searchUser = [{}][{}]", searchedUser.getUserId(), searchedUser.getUserPassword());
			if (searchedUser.getUserPassword()// 가져온 유저의 비밀번호가
					.equals(targetUser.getUserPassword())) {//입력한 유저의 비밀번호와 일치하면
				lsDTO.setIsLoginConfirmed(true);//같으면 로그인여부 DTO에 true로 설정

				HttpSession session = httpServletRequest.getSession();
				session.setAttribute("MenMeetSession", searchedUser);
				User menMeetSession = (User) session.getAttribute("MenMeetSession");
				log.info("사용자 로그인 정보 : ID : [{}] 닉네임 : [{}]", menMeetSession.getUserId(), menMeetSession.getUserName());

			} else {//비밀 번호가 일치하지 않으면 실패 리턴
				lsDTO.setIsLoginConfirmed(false);
			}
		}
		return lsDTO;
	}
}
