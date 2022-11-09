package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.signup.SignUpCheckIdDTO;
import mentoss.menmeet.DTO.signup.SignUpCheckNameDTO;
import mentoss.menmeet.DTO.signup.SignUpConfirmDTO;
import mentoss.menmeet.entity.User;
import mentoss.menmeet.service.SignUpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/signup")
@RestController
@RequiredArgsConstructor
public class SignUpController {
	private final SignUpService signUpService;

	//회원가입시 Id 중복 확인
	@GetMapping("/checkDuplicateId")
	public SignUpCheckIdDTO checkUserId(@RequestParam String userId) {
		return signUpService.checkId(userId);
	}

	//회원가입시 Name 중복 확인
	@GetMapping("/checkDuplicateName")
	public SignUpCheckNameDTO checkUserName(@RequestParam String userName) {
		return signUpService.checkName(userName);
	}

	//회원 가입 처리 json
	@PostMapping(consumes = "application/json")
	public SignUpConfirmDTO signUpHandlerJsonRequest(@RequestBody User targetUser) {
		return signUpService.signUp(targetUser);
	}

	//회원 목록 보기
	@GetMapping("/userList")
	public List<User> userList() {
		return signUpService.showUserList();
	}

}
