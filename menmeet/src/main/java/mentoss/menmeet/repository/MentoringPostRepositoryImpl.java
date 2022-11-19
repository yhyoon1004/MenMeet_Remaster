package mentoss.menmeet.repository;

import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.entity.PostCount;
import mentoss.menmeet.entity.MentoringPost;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class MentoringPostRepositoryImpl implements MentoringPostRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void savePost(MentoringPost post) {
		entityManager.persist(post);
		log.info("MentoringPostRepositoryImpl.savePost");
	}

	@Override
	public void deletePost(Integer postNum) {
		MentoringPost findPost = entityManager.find(MentoringPost.class, postNum);
		entityManager.remove(findPost);
		log.info("MentoringPostRepositoryImpl");
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
		log.info("MentoringPostRepositoryImpl.updatePost");
			return true;
	}

	@Override
	public Optional<MentoringPost> findPostByPostNum(Integer postNum) {
		MentoringPost findPost = entityManager.find(MentoringPost.class, postNum);
		log.info("MentoringPostRepositoryImpl.findPostByPostNum");
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
		log.info("called : MentoringPostRepositoryImpl.findPosts");
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
		log.info("called : MentoringPostRepositoryImpl.getPostCount");
		return postCount.getTotal_count();
	}
}
