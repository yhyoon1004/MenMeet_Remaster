package mentoss.menmeet.repository;

import mentoss.menmeet.DTO.post.PostIndexDTO;
import mentoss.menmeet.domain.MentoringPost;

import java.util.List;
import java.util.Optional;

public interface MentoringPostRepository {
	//게시물 저장
	void savePost(MentoringPost post);

	//게시물 번호가 높은 순서로 게시물 목차 여러개 조회
	List<PostIndexDTO> findTotalPostsOrderByPostNum(Integer pageNum);

	//키워드로 게시물 목차 여러개 조회
	List<MentoringPost> findPostsByKeyword(String keyword, Integer pageNum);

	//게시물 번호로 조회 단일 게시물내용 조회
	Optional<MentoringPost> findPostByPostNum(Integer postNum, Integer pageNum);

}
