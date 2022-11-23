package mentoss.menmeet.service;

import mentoss.menmeet.DTO.myPage.*;

import java.util.List;

public interface MyPageService {
	CheckPasswordStateDTO checkPassword(String userPassword);

	ChangeStateDTO changePassword(String userPassword);

	UserWithdrawStateDTO withdrawUser(String userId, String userPassword);

	//사용자가 작성한 게시물 조회
	List<MyMentoringPostIndexDTO> showUserPosts(String userId);

	//내가 신청한 멘토링 조회
	List<AppliedMentoringDTO> showMentoringMyApplication(String userId);

	//신청 받은 멘토링 조회
	List<ReceivedMentoringDTO> showReceivedMentoring(String userId);

}
