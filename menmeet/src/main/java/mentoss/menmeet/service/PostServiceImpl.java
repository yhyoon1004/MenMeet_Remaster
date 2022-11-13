package mentoss.menmeet.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.entity.User;
import mentoss.menmeet.repository.MentoringPostRepository;
import mentoss.menmeet.session.MenMeetSessionCont;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
	private final HttpSession httpSession;
	private final MentoringPostRepository mentoringPostRepository;

	public List<PostIndexDTO> dtoListMapper(List<MentoringPost> targetList){
		List<PostIndexDTO> piList = new ArrayList<>();
		PostIndexDTO piDTO;
		for (MentoringPost mentoringPost : targetList) {
			piDTO = new PostIndexDTO();
			piDTO.setPostNum(mentoringPost.getPostNum());
			piDTO.setPostTitle(mentoringPost.getTitle());
			piDTO.setCategory(mentoringPost.getCategory());
			piDTO.setPostIsMentor(mentoringPost.getMentoringTarget());
			piDTO.setPostWriterName(mentoringPost.getWriterName());
			piDTO.setMentoringTime(mentoringPost.getMentoringTime());
			piDTO.setMentoringEnable(
					mentoringPost.getMentoringEnable() == 0 ? true : false);
			piDTO.setPostWriteTime(mentoringPost.getPostingTime());
			piList.add(piDTO);
		}
		return piList;
	}

	@Override
	public List<PostIndexDTO> showPostIndexList(@Nullable Integer category, @Nullable Integer isMentor,@Nullable String keyword, @Nullable Integer pageNum) {
		List<MentoringPost> repositoryPosts = mentoringPostRepository.findPosts(category, isMentor, keyword, pageNum);
		return dtoListMapper(repositoryPosts);
	}

	@Override
	public PostContentDTO showPostContent(Integer postNum) {
		MentoringPost findPost = mentoringPostRepository.findPostByPostNum(postNum).get();
		PostContentDTO resultDTO = PostContentDTO.builder()
				.postNum(findPost.getPostNum())
				.writerName(findPost.getWriterName())
				.category(findPost.getCategory())
				.postIsMentor(findPost.getMentoringTarget()==1?true:false)
				.title(findPost.getTitle())
				.content(findPost.getContent())
				.mentoringEnable(findPost.getMentoringEnable()==0?true:false)
				.mentoringTime(findPost.getMentoringTime())
				.postWriteTime(findPost.getPostingTime())
				.build();
		return resultDTO;
	}

	@Override
	public PostCreateStateDTO createUserPost(MentoringPost mentoringPost) {
		mentoringPostRepository.savePost(mentoringPost);
		Optional<MentoringPost> postByPostNum = mentoringPostRepository.findPostByPostNum(mentoringPost.getPostNum());
		PostCreateStateDTO postCreateStateDTO;
		if (postByPostNum.isPresent())
			postCreateStateDTO =new PostCreateStateDTO(true);
		else
			postCreateStateDTO = new PostCreateStateDTO(false);


		return postCreateStateDTO;
	}

	@Override
	public PostUpdateStateDTO updateUserPost(MentoringPost mentoringPost) {
		return new PostUpdateStateDTO(mentoringPostRepository.updatePost(mentoringPost));
	}

	@Override
	public PostDeleteStateDTO deleteUserPost(Integer postNum) {
		System.out.println("postNum"+postNum);
		PostDeleteStateDTO postDeleteStateDTO;
		User user = (User) httpSession.getAttribute(MenMeetSessionCont.LOGIN_SESSION);
		System.out.println(user.getUserId());
		Optional<MentoringPost> searchedPost = mentoringPostRepository.findPostByPostNum(postNum);
		if (searchedPost.isPresent()){//해당 게시물 존재 여부확인
			if(searchedPost.get().getWriterId().equals(user.getUserId())){
				//로그인한 유저가 작성한 게시물인지 확인
				mentoringPostRepository.deletePost(postNum);
				postDeleteStateDTO = new PostDeleteStateDTO(true);
			}else {
				postDeleteStateDTO = new PostDeleteStateDTO(false);
			}
		}else {
			postDeleteStateDTO = new PostDeleteStateDTO(false);
		}
		return postDeleteStateDTO;
	}
}
