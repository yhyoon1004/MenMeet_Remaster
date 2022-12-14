package mentoss.menmeet.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.entity.PostCount;
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

@Slf4j
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
		log.info("called : PostServiceImpl.dtoListMapper");
		return piList;
	}

	@Override
	public List<PostIndexDTO> showPostIndexList( Integer category,  Integer isMentor, String keyword, Integer pageNum) {
		List<MentoringPost> repositoryPosts = mentoringPostRepository.findPosts(category, isMentor, keyword, pageNum);
		log.info("called : PostServiceImpl.showPostIndexList");
		return dtoListMapper(repositoryPosts);
	}

	@Override
	public PostCount showPostCount(Integer category, Integer isMentor, String keyword) {
		 PostCount postCount = new PostCount();
		 postCount.setTotal_count(mentoringPostRepository.getPostCount(category,isMentor,keyword));
		log.info("called : PostServiceImpl.showPostCount");
		 return postCount;
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
		log.info("called : PostServiceImpl.showPostContent");
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

		log.info("called : PostServiceImpl.createUserPost");
		return postCreateStateDTO;
	}

	@Override
	public PostUpdateStateDTO updateUserPost(MentoringPost mentoringPost) {
		PostUpdateStateDTO postUpdateStateDTO;
		//????????? ????????? ?????? ????????? ??????
		Optional<MentoringPost> postByPostNum = mentoringPostRepository.findPostByPostNum(mentoringPost.getPostNum());

		if (!postByPostNum.isPresent()){//?????? ????????? ????????? ???????????? ?????? ??? ????????? ?????? ??????
			log.info("called : PostServiceImpl.");
			return new PostUpdateStateDTO(false);
		}
		MentoringPost targetPost = postByPostNum.get();
		User user = (User) httpSession.getAttribute(MenMeetSessionCont.LOGIN_SESSION);//????????? ??????????????? ????????? ?????????

		if(targetPost.getWriterId().equals(user.getUserId())){//???????????? ????????? ID??? ????????? ????????? ID??? ??????
			postUpdateStateDTO =
					new PostUpdateStateDTO(mentoringPostRepository.updatePost(mentoringPost));
		}else {
			postUpdateStateDTO = new PostUpdateStateDTO(false);
		}
		log.info("called : PostServiceImpl.updateUserPost");
		return postUpdateStateDTO;
	}

	@Override
	public PostDeleteStateDTO deleteUserPost(Integer postNum) {
		System.out.println("postNum"+postNum);
		PostDeleteStateDTO postDeleteStateDTO;
		User user = (User) httpSession.getAttribute(MenMeetSessionCont.LOGIN_SESSION);
		System.out.println(user.getUserId());
		Optional<MentoringPost> searchedPost = mentoringPostRepository.findPostByPostNum(postNum);
		if (searchedPost.isPresent()){//?????? ????????? ?????? ????????????
			if(searchedPost.get().getWriterId().equals(user.getUserId())){
				//???????????? ????????? ????????? ??????????????? ??????
				mentoringPostRepository.deletePost(postNum);
				postDeleteStateDTO = new PostDeleteStateDTO(true);
			}else {
				postDeleteStateDTO = new PostDeleteStateDTO(false);
			}
		}else {
			postDeleteStateDTO = new PostDeleteStateDTO(false);
		}
		log.info("called : PostServiceImpl.deleteUserPost");
		return postDeleteStateDTO;
	}
}
