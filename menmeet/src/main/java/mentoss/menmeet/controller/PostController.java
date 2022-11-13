package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.post.*;
import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.repository.MentoringPostRepository;
import mentoss.menmeet.service.PostService;
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
	 * pageNum = 1부터
	 */
	@PostMapping(value = "/", consumes = "application/json")
	public List<PostIndexDTO> postList(@RequestBody PostSearchDTO postSearchDTO) {
		return postService.showPostIndexList(postSearchDTO.getCategory(), postSearchDTO.getMentor(), postSearchDTO.getKeyword(), postSearchDTO.getPageNum());
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
	@PostMapping(value = "/createPost", consumes = "application/json")
	public PostCreateStateDTO createPost(@RequestBody MentoringPost mentoringPost) {
		return postService.createUserPost(mentoringPost);
	}

	/*
	 **********게시글 수정**********
	 */
	@PostMapping(value = "/updatePost", consumes = "application/json")
	public PostUpdateStateDTO updatePost(@RequestBody MentoringPost mentoringPost) {
		return postService.updateUserPost(mentoringPost);
	}

	/*
	 **********게시글 삭제**********
	 */
	@DeleteMapping(value = "/deletePost/{postNum}")
	public PostDeleteStateDTO deletePost(@PathVariable int postNum) {
		return postService.deleteUserPost(postNum);
	}
}
