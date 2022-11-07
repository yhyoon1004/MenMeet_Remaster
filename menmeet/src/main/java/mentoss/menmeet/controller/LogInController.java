package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.login.LoginFormDTO;
import mentoss.menmeet.DTO.login.LoginStateDTO;
import mentoss.menmeet.DTO.login.LogoutStateDTO;
import mentoss.menmeet.domain.User;
import mentoss.menmeet.service.LoginService;
import mentoss.menmeet.session.MenMeetSessionCont;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LogInController {
	private final LoginService loginService;
	private final HttpServletRequest request;


	@PostMapping(value = "/login", consumes = "application/json")
	public LoginStateDTO login(@RequestBody LoginFormDTO user) {
		return loginService.confirmLogin(user);
	}

	@PostMapping(value = "/logout")
	public LogoutStateDTO logout() {
		HttpSession httpSession = request.getSession();
		if (httpSession != null)
			httpSession.invalidate();
		return new LogoutStateDTO(true);
	}
}
