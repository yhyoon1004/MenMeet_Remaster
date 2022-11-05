package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.post.PostContentDTO;
import mentoss.menmeet.DTO.post.PostIndexDTO;
import mentoss.menmeet.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	/*
	 **********전체 게시글 목록 조회**********
	 */
	@GetMapping(value = "/post/allList/{pageNum}")
	public List<PostIndexDTO> mainPostList(@PathVariable int pageNum ) {
		return postService.showAllPostList(pageNum);
	}
	/*
	 **********카테고리 별 게시글 목록 조회**********
	 */
	@GetMapping(value = "/post/category/{category}/{mentor}")
	public List<PostIndexDTO> categoryPostList(@PathVariable int category, @PathVariable int mentor, @PathVariable int pageNum) {
		return postService.showCategoryPostList(category, mentor, pageNum);
	}
	/*
	 **********키워드 검색한 게시글 목록 조회**********
	 */
	@GetMapping(value="/post/search/{keyword}/{pageNum}")
	public List<PostIndexDTO> searchedPost(@PathVariable String keyword, @PathVariable int pageNum){
		return postService.showSearchedPostList(keyword, pageNum);
	}

	/*
	 **********게시글 내용 조회**********
	 */
	@GetMapping(value="/post/showPost/{postNum}")
	public PostContentDTO postContent(@PathVariable Integer postNum){
		return postService.showPostContent(postNum);
	}


}
