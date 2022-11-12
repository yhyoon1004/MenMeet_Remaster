package mentoss.menmeet.repository;

import mentoss.menmeet.DTO.post.PostIndexDTO;
import mentoss.menmeet.entity.MentoringPost;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MentoringPostRepositoryImpl implements MentoringPostRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void savePost(MentoringPost post) {
		entityManager.persist(post);
	}

	@Override
	public void deletePost(Integer postNum) {
		MentoringPost findPost = entityManager.find(MentoringPost.class, postNum);
		entityManager.remove(findPost);
	}

	@Override
	public Boolean updatePost(MentoringPost post) {
		Optional<MentoringPost> mentoringPost = Optional.ofNullable(entityManager.find(MentoringPost.class, post));
		if(!mentoringPost.isPresent()) {
			System.out.println("failed to update Post. cause by this post is not present"+post.getPostNum());
			return false;
		}else {
			entityManager.persist(post);
			return true;
		}
	}

	@Override
	public Optional<MentoringPost> findPostByPostNum(Integer postNum) {
		MentoringPost findPost = entityManager.find(MentoringPost.class, postNum);
		return Optional.ofNullable(findPost);
	}

	@Override
	public List<PostIndexDTO> findPosts(Integer category, Integer isMentor, String keyword, Integer pageNum) {
		return null;
	}
}
