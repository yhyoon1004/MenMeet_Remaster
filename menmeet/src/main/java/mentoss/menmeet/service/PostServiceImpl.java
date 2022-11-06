package mentoss.menmeet.service;

import mentoss.menmeet.DTO.post.PostContentDTO;
import mentoss.menmeet.DTO.post.PostIndexDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

	@Override
	public List<PostIndexDTO> showAllPostList(int pageNum) {

		return null;
	}

	@Override
	public List<PostIndexDTO> showCategoryPostList(int category, int mentor, int pageNum) {
		return null;
	}

	@Override
	public List<PostIndexDTO> showSearchedPostList(String keyword, int pageNum) {
		return null;
	}

	@Override
	public PostContentDTO showPostContent(int postNum) {
		return null;
	}
}
