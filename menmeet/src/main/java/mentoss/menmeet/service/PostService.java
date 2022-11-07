package mentoss.menmeet.service;

import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.domain.MentoringPost;

import java.util.List;

/*
reference Note
	1. 게시물은 10개씩 List에 담아서 제공

 */
public interface PostService {
	/*
	 *****전체 멘토링 게시물 조회**********
	 * pageNum = 요청할 페이지
	 */
	List<PostIndexDTO> showAllPostList(int pageNum);

	/*
	 **********카테고리별 게시물 조회**********
	 * category = 멘토링 카테고리
	 * mentor = 0-멘티/1-멘토
	 * pageNum = 요청할 페이지
	 */
	List<PostIndexDTO> showCategoryPostList(int category, int mentor, int pageNum);

	/*
	 **********키워드 검색 게시물 조회**********
	 * keyword = 검색할 키워드문자
	 * mentor = 0-멘티/1-멘토
	 * pageNum = 요청할 페이지
	 */
	List<PostIndexDTO> showSearchedPostList(String keyword, int pageNum);

	/*
	 **********게시물 하나 보기**********
	 */
	PostContentDTO showPostContent(int postNum);

	/*
	 **********게시물 등록하기**********
	 */
	PostCreateStateDTO createUserPost(MentoringPost mentoringPost);

	PostUpdateStateDTO updateUserPost(PostUpdateFormDTO postUpdateFormDTO);

	PostDeleteStateDTO deleteUserPost(int postNum, String userId);
}
