package mentoss.menmeet.repository;

import mentoss.menmeet.entity.PostCount;
import mentoss.menmeet.entity.MentoringPost;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
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
		MentoringPost mentoringPost = entityManager.find(MentoringPost.class, post.getPostNum());
			mentoringPost.setCategory(post.getCategory());
			mentoringPost.setMentoringTarget(post.getMentoringTarget());
			mentoringPost.setTitle(post.getTitle());
			mentoringPost.setContent(post.getContent());
			mentoringPost.setMentoringEnable(post.getMentoringEnable());
			mentoringPost.setPostingTime(post.getPostingTime());
			mentoringPost.setMentoringTime(post.getMentoringTime());
			entityManager.flush();
			return true;
	}

	@Override
	public Optional<MentoringPost> findPostByPostNum(Integer postNum) {
		MentoringPost findPost = entityManager.find(MentoringPost.class, postNum);
		return Optional.ofNullable(findPost);
	}

	@Override
	public List<MentoringPost> findPosts(Integer category, Integer isMentor, String keyword, Integer pageNum) {
		StoredProcedureQuery postListProcedure = entityManager.createNamedStoredProcedureQuery("postListProcedure");
		StoredProcedureQuery storedProcedureQuery = postListProcedure
				.setParameter("_category", category)
				.setParameter("_isMentor", isMentor)
				.setParameter("_keyword", keyword)
				.setParameter("_pageNum", pageNum);
		List<MentoringPost> resultList = storedProcedureQuery.getResultList();
		return resultList;
	}

	@Override
	public Integer getPostCount(Integer category, Integer isMentor, String keyword) {
		StoredProcedureQuery postCountProcedure = entityManager.createNamedStoredProcedureQuery("postCountProcedure");
		StoredProcedureQuery storedProcedureQuery = postCountProcedure
				.setParameter("_category", category)
				.setParameter("_isMentor", isMentor)
				.setParameter("_keyword", keyword);
		PostCount postCount =(PostCount)storedProcedureQuery.getSingleResult();
		return postCount.getTotal_count();
	}
}
