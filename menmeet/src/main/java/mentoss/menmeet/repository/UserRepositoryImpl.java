package mentoss.menmeet.repository;

import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveUser(User saveUser) {
		entityManager.persist(saveUser);
		log.info("called : UserRepositoryImpl.saveUser");
	}

	@Override
	public Optional<User> findUserById(String targetUserId) {
		User findUser = entityManager.find(User.class, targetUserId);
		log.info("called : UserRepositoryImpl.findUserById");
		return Optional.ofNullable(findUser);
	}

	@Override
	public Optional<User> findUserByName(String targetUserName) {
		Optional<User> findUser = entityManager.createQuery("select m from User as m  where m.userName = :name", User.class)
				.setParameter("name", targetUserName).getResultStream().findAny();
		log.info("called : UserRepositoryImpl.findUserByName");
		return findUser;
	}

	@Override
	public List<User> findUserAll() {
		List<User> findUserList = entityManager.createQuery("select u from User as u", User.class).getResultList();
		log.info("called : UserRepositoryImpl.findUserAll");
		return findUserList;
	}

	@Override
	public void deleteUser(String deleteUser) {
		User findDeleteUser = entityManager.find(User.class, deleteUser);
		entityManager.remove(findDeleteUser);
		log.info("called : UserRepositoryImpl.deleteUser");
	}

	@Override
	public void changeUserPassword(String userId, String changePassword) {
		User changeTargetUser = entityManager.find(User.class, userId);
		changeTargetUser.setUserPassword(changePassword);
		entityManager.flush();
		log.info("called : UserRepositoryImpl.changeUserPassword");
	}
}