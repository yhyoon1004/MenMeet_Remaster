package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.post.MentoringPostIndex;
import mentoss.menmeet.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/myPage")
@RestController
@RequiredArgsConstructor
public class MyPageController {
	private final PostService postService;

	//내가 작성한 게시물 조회
	@GetMapping("/myPosts/{userId}")
	public List<MentoringPostIndex> showMyPosts(@PathVariable String userId){
		return postService.showUserPosts(userId);
	}


}
