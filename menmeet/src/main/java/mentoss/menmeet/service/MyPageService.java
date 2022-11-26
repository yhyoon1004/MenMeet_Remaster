package mentoss.menmeet.service;

import mentoss.menmeet.DTO.myPage.*;

import java.util.List;

public interface MyPageService {
	//비밀번호 확인
	CheckPasswordStateDTO checkPassword(CheckUserPwdFormDTO userInfo);
	//비밀번호 변경
	ChangeStateDTO changePassword(ChangeUserPwdFormDTO userInfo);

	UserWithdrawStateDTO withdrawUser(String userId, String userPassword);

	//사용자가 작성한 게시물 조회
	List<MyMentoringPostIndexDTO> showUserPosts(String userId);

	//내가 신청한 멘토링 조회
	List<MentoringApplicationDTO> showMentoringMyApplication(String userId);

	//신청 받은 멘토링 조회
	List<MentoringApplicationDTO> showReceivedMentoring(String userId);

	//나의 멘토링 예약 정보확인
	List<MyReservationDTO> showMyReservation(String userId);
}
