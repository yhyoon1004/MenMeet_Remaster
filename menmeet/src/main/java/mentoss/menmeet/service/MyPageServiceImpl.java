package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.myPage.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.repository.MentoringPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
	private final MentoringPostRepository mentoringPostRepository;

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
	public List<ApplicatedMentoringDTO> showMentoringMyApplication(String userId) {
		return null;
	}

	@Override
	public List<ReceivedMentoringDTO> showReceivedMentoring(String userId) {
		return null;
	}
}
