package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.domain.MentoringPost;
import mentoss.menmeet.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	/*
	 **********전체 게시글 목록 조회**********
	 */
	@GetMapping(value = "/allList/{pageNum}")
	public List<PostIndexDTO> mainPostList(@PathVariable int pageNum) {
		return postService.showAllPostList(pageNum);
	}

	/*
	 **********카테고리 별 게시글 목록 조회**********
	 */
	@GetMapping(value = "/category/{category}/{mentor}")
	public List<PostIndexDTO> categoryPostList(@PathVariable int category, @PathVariable int mentor, @PathVariable int pageNum) {
		return postService.showCategoryPostList(category, mentor, pageNum);
	}

	/*
	 **********키워드 검색한 게시글 목록 조회**********
	 */
	@GetMapping(value = "/search/{keyword}/{pageNum}")
	public List<PostIndexDTO> searchedPost(@PathVariable String keyword, @PathVariable int pageNum) {
		return postService.showSearchedPostList(keyword, pageNum);
	}

	/*
	 **********게시글 내용 조회**********
	 */
	@GetMapping(value = "/showPost/{postNum}")
	public PostContentDTO postContent(@PathVariable Integer postNum) {
		return postService.showPostContent(postNum);
	}

	/*
	 **********게시글 등록**********
	 */
	@PostMapping(value="/createPost",consumes = "application/json")
	public PostCreateStateDTO createPost(@RequestBody MentoringPost mentoringPost){
		return postService.createUserPost(mentoringPost);
	}
	/*
	 **********게시글 수정**********
	 */
	@PostMapping(value = "/updatePost",consumes = "application/json")
	public PostUpdateStateDTO updatePost(@RequestBody PostUpdateFormDTO postUpdateFormDTO){
		return postService.updateUserPost(postUpdateFormDTO);
	}

	/*
	 **********게시글 삭제**********
	 */
	@PostMapping(value = "/deletePost",consumes = "application/json")
	public PostDeleteStateDTO deletePost(@RequestBody int postNum,@RequestBody String userId){
		return postService.deleteUserPost(postNum, userId);
	}
}
