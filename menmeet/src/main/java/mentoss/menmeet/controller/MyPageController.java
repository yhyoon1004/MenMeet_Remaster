package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.myPage.*;
import mentoss.menmeet.service.MyPageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/myPage")
@RestController
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;

	//비밀번호 확인
	@PostMapping(value = "/checkPassword",consumes = "application/json")
	public CheckPasswordStateDTO checkPassword(@RequestBody CheckUserPwdFormDTO checkUserPwdFormDTO ){
		return myPageService.checkPassword(checkUserPwdFormDTO);
	}

	//비밀번호 변경
	@PostMapping(value = "/changePassword", consumes = "application/json")
	public ChangeStateDTO changePassword(@RequestBody ChangeUserPwdFormDTO changeUserPwdFormDTO){
		return myPageService.changePassword(changeUserPwdFormDTO);
	}

	//회원탈퇴
	@PostMapping(value = "/withdrawUser", consumes = "application/json")
	public UserWithdrawStateDTO withdrawUser(@RequestBody WithdrawUserFormDTO withdrawUserFormDTO){
		return myPageService.withdrawUser(withdrawUserFormDTO.getUserId(), withdrawUserFormDTO.getUserPassword());
	}

	//내가 작성한 게시물 조회
	@GetMapping("/myPosts/{userId}")
	public List<MyMentoringPostIndexDTO> showMyPosts(@PathVariable String userId){
		return myPageService.showUserPosts(userId);
	}
	
	//내가 신청한 멘토링 요청 조회
	@GetMapping("/myAppliedMentoring/{userId}")
	public List<MentoringApplicationDTO> showMyApplications(@PathVariable String userId){
		return myPageService.showMentoringMyApplication(userId);
	}

	//신청받은 멘토링 요청 조회
	@GetMapping("/myReceivedMentoring/{userId}")
	public List<MentoringApplicationDTO> showReceivedApplications(@PathVariable String userId){
		return myPageService.showReceivedMentoring(userId);
	}

	//나의 예약 내역
	@GetMapping("/myReservation/{userId}")
	public List<MyReservationDTO> showMyReservation(@PathVariable String userId){
		return myPageService.showMyReservation(userId);
	}


}
