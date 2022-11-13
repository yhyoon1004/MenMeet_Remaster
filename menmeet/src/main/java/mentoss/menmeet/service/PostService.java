package mentoss.menmeet.service;

import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.entity.MentoringPost;

import java.util.List;

/*
reference Note
	1. 게시물은 10개씩 List에 담아서 제공

 */
public interface PostService {
	/*
	 **********멘토링 게시물 목록 조회**********
	 */
	List<PostIndexDTO> showPostIndexList(Integer category, Integer isMentor,String keyword, Integer pageNum);

	/*
	 **********게시물 하나 보기**********
	 */
	PostContentDTO showPostContent(Integer postNum);

	/*
	 **********게시물 등록하기**********
	 */
	PostCreateStateDTO createUserPost(MentoringPost mentoringPost);

	PostUpdateStateDTO updateUserPost(MentoringPost postUpdateFormDTO);

	PostDeleteStateDTO deleteUserPost(Integer postNum);
}
