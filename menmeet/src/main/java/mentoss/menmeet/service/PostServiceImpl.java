package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.repository.MentoringPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final MentoringPostRepository mentoringPostRepository;

	@Override
	public List<PostIndexDTO> showPostIndexList(Integer category, Integer isMentor, Integer pageNum) {

		return null;
	}

	@Override
	public List<PostIndexDTO> showSearchedPostIndexList(Integer category, Integer isMentor, Integer pageNum, String keyword) {
		return null;
	}

	@Override
	public PostContentDTO showPostContent(int postNum) {
		return null;
	}

	@Override
	public PostCreateStateDTO createUserPost(MentoringPost mentoringPost) {
		return null;
	}

	@Override
	public PostUpdateStateDTO updateUserPost(PostUpdateFormDTO postUpdateFormDTO) {
		return null;
	}

	@Override
	public PostDeleteStateDTO deleteUserPost(int postNum, String userId) {
		return null;
	}
}
