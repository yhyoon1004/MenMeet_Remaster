package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.login.LoginFormDTO;
import mentoss.menmeet.DTO.login.LoginStateDTO;
import mentoss.menmeet.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogInController {
	private final LoginService loginService;
	@PostMapping(value = "/login", consumes = "application/json")
	public LoginStateDTO login(@RequestBody LoginFormDTO user){
		return loginService.confirmLogin(user);
	}

}
