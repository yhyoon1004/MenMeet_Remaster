package mentoss.menmeet.repository;

import mentoss.menmeet.DTO.post.PostIndexDTO;
import mentoss.menmeet.entity.MentoringPost;

import java.util.List;
import java.util.Optional;

public interface MentoringPostRepository {
	//게시물 저장
	void savePost(MentoringPost post);

	//게시물 삭제
	void deletePost(Integer postNum);

	//게시물 수정
	Boolean updatePost(MentoringPost post);

	//단일 게시물 내용 조회
	Optional<MentoringPost> findPostByPostNum(Integer postNum);

	//게시물 목록 조회
	List<MentoringPost> findPosts(Integer category, Integer isMentor, String keyword, Integer pageNum);

	Integer getPostCount(Integer category, Integer isMentor, String keyword);

	//사용자가 작성한 게시물 조회
	List<MentoringPost> findPostsByOwnerId(String userId);
}
