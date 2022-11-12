package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.service.PostService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mentoringPost")
@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	/*
	 **********게시글 목록 조회**********
	 * category = 0 전체
	 * mentor = 0 전체
	 * pageNum = 0 첫번째
	 */
	@GetMapping(value = "/{category}/{mentor}/{pageNum}")
	public List<PostIndexDTO> postList(@PathVariable Integer category,
	                                   @PathVariable Integer mentor,
	                                   @PathVariable Integer pageNum) {
		System.out.println("category = " + category + " mentor = " + mentor + " pageNum = " + pageNum);
		return postService.showPostIndexList(category, mentor, pageNum);
	}

	/*
	 *게시글 검색
	 */
	@GetMapping(value = "/{category}/{mentor}/{pageNum}/{keyword}")
	public List<PostIndexDTO> searchPostList(@PathVariable Integer category,
	                                         @PathVariable Integer mentor,
	                                         @PathVariable Integer pageNum,
	                                         @PathVariable String keyword) {
		System.out.println("category = " + category + " mentor = " + mentor + " pageNum = " + pageNum + " keyword = " + keyword);
		return postService.showSearchedPostIndexList(category, mentor, pageNum, keyword);
	}

//	/*
//	 **********게시글 내용 조회**********
//	 */
//	@GetMapping(value = "/showPost/{postNum}")
//	public PostContentDTO postContent(@PathVariable Integer postNum) {
//		return postService.showPostContent(postNum);
//	}
//
//	/*
//	 **********게시글 등록**********
//	 */
//	@PostMapping(value = "/createPost", consumes = "application/json")
//	public PostCreateStateDTO createPost(@RequestBody MentoringPost mentoringPost) {
//		return postService.createUserPost(mentoringPost);
//	}
//
//	/*
//	 **********게시글 수정**********
//	 */
//	@PostMapping(value = "/updatePost", consumes = "application/json")
//	public PostUpdateStateDTO updatePost(@RequestBody PostUpdateFormDTO postUpdateFormDTO) {
//		return postService.updateUserPost(postUpdateFormDTO);
//	}
//
//	/*
//	 **********게시글 삭제**********
//	 */
//	@PostMapping(value = "/deletePost", consumes = "application/json")
//	public PostDeleteStateDTO deletePost(@RequestBody int postNum, @RequestBody String userId) {
//		return postService.deleteUserPost(postNum, userId);
//	}
}
