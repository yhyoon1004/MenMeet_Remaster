package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.myPage.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.entity.Reservation;
import mentoss.menmeet.entity.ReservationSubscription;
import mentoss.menmeet.repository.MentoringPostRepository;
import mentoss.menmeet.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	//내가 신청한 멘토링 조회
	@Override
	public List<MentoringApplicationDTO> showMentoringMyApplication(String userId) {
		List<ReservationSubscription> myMentoringApplyList = reservationRepository.showMyApplyMentoringList(userId);
		List<MentoringApplicationDTO> targetMyList = new ArrayList<>();
		MentoringApplicationDTO maDTO;
		for (ReservationSubscription rs : myMentoringApplyList) {
			maDTO = new MentoringApplicationDTO();
			maDTO.setSubscriptNum(rs.getNum());
			maDTO.setPostNum(rs.getPostNum());
			maDTO.setState(rs.getIsAccept());
			maDTO.setApplyTime(rs.getRequestTime());
			MentoringPost postByPostNum = mentoringPostRepository.findPostByPostNum(rs.getPostNum()).get();
			maDTO.setPostTitle(postByPostNum.getTitle());
			maDTO.setIsMentor(postByPostNum.getMentoringTarget()==0?false:true);
			targetMyList.add(maDTO);
		}

		return targetMyList;
	}

	//내가 받은 멘토링 신청 조회
	@Override
	public List<MentoringApplicationDTO> showReceivedMentoring(String userId) {
		List<MentoringApplicationDTO> resultList = new ArrayList<>();
		MentoringApplicationDTO maDTO;

		List<MentoringPost> postsByOwnerId = mentoringPostRepository.findPostsByOwnerId(userId);
		List<ReservationSubscription> myRSList =new ArrayList<>();

		for (MentoringPost post : postsByOwnerId) {
			myRSList
					.addAll(reservationRepository.showReceivedMentoringRequest(post.getPostNum()));
		}
		for (ReservationSubscription rs : myRSList) {
			maDTO = new MentoringApplicationDTO();
			maDTO.setSubscriptNum(rs.getNum());
			maDTO.setPostNum(rs.getPostNum());
			maDTO.setState(rs.getIsAccept());
			maDTO.setApplyTime(rs.getRequestTime());
			MentoringPost postByPostNum = mentoringPostRepository.findPostByPostNum(rs.getPostNum()).get();
			maDTO.setPostTitle(postByPostNum.getTitle());
			maDTO.setIsMentor(postByPostNum.getMentoringTarget()==0?false:true);
			resultList.add(maDTO);
		}

		return resultList;
	}

	//나의 멘토링 정보확인
	@Override
	public List<MyReservationDTO> showMyReservation(String userId){
		List<Reservation> reservationList = reservationRepository.searchReservationByUserId(userId);
		List<MyReservationDTO> resultList = new ArrayList<>();
		MyReservationDTO mrDTO;
		for (Reservation box : reservationList) {
			MentoringPost post = mentoringPostRepository.findPostByPostNum(box.getMentoringPostNum()).get();
			mrDTO = MyReservationDTO.builder()
					.reservationNum(box.getReservationNum())
					.postNum(box.getMentoringPostNum())
					.category(box.getCategory())
					.mentorId(box.getMentor())
					.menteeId(box.getMentee())
					.title(post.getTitle())
					.time(box.getMentoringTime())
					.build();
			resultList.add(mrDTO);
		}

		return resultList;
	}
}
