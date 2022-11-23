package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.myPage.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.entity.ReservationSubscription;
import mentoss.menmeet.repository.MentoringPostRepository;
import mentoss.menmeet.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
	private final MentoringPostRepository mentoringPostRepository;
	private final ReservationRepository reservationRepository;

	@Override
	public CheckPasswordStateDTO checkPassword(String userPassword) {
		return null;
	}

	@Override
	public ChangeStateDTO changePassword(String userPassword) {
		return null;
	}

	@Override
	public UserWithdrawStateDTO withdrawUser(String userId, String userPassword) {
		return null;
	}

	//사용자가 작성한 게시물 조회
	@Override
	public List<MyMentoringPostIndexDTO> showUserPosts(String userId){
		List<MentoringPost> postsByOwnerId = mentoringPostRepository.findPostsByOwnerId(userId);
		List<MyMentoringPostIndexDTO> resultList = new ArrayList<>();
		MyMentoringPostIndexDTO mentoringPostIndex;
		for (MentoringPost post : postsByOwnerId) {
			mentoringPostIndex =new MyMentoringPostIndexDTO();
			mentoringPostIndex.setPostNum(post.getPostNum());
			mentoringPostIndex.setIsMentorPost(post.getMentoringTarget());
			mentoringPostIndex.setTitle(post.getTitle());
			mentoringPostIndex.setWriteTime(post.getPostingTime());
			resultList.add(mentoringPostIndex);
		}
		return resultList;
	}

	@Override
	public List<AppliedMentoringDTO> showMentoringMyApplication(String userId) {
		List<ReservationSubscription> myMentoringApplyList = reservationRepository.showMyApplyMentoringList(userId);
		List<AppliedMentoringDTO> targetMyList = new ArrayList<>();
		AppliedMentoringDTO amDTO;
		for (ReservationSubscription rs : myMentoringApplyList) {
			amDTO = new AppliedMentoringDTO();
			amDTO.setSubscriptNum(rs.getNum());
			amDTO.setPostNum(rs.getPostNum());
			amDTO.setState(rs.getIsAccept());
			amDTO.setApplyTime(rs.getRequestTime());
			MentoringPost postByPostNum = mentoringPostRepository.findPostByPostNum(rs.getPostNum()).get();
			amDTO.setPostTitle(postByPostNum.getTitle());
			amDTO.setIsMentor(postByPostNum.getMentoringTarget()==0?false:true);
			targetMyList.add(amDTO);
		}

		return targetMyList;
	}

	@Override
	public List<ReceivedMentoringDTO> showReceivedMentoring(String userId) {
		return null;
	}
}
