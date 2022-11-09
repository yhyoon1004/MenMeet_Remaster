package mentoss.menmeet.repository;

import mentoss.menmeet.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveUser(User saveUser) {
		entityManager.persist(saveUser);
	}

	@Override
	public Optional<User> findUserById(String targetUserId) {
		User findUser = entityManager.find(User.class, targetUserId);
		return Optional.ofNullable(findUser);
	}

	@Override
	public Optional<User> findUserByName(String targetUserName) {
		Optional<User> findUser = entityManager.createQuery("select m from User as m  where m.userName = :name", User.class)
				.setParameter("name", targetUserName).getResultStream().findAny();
		return findUser;
	}

	@Override
	public List<User> findUserAll() {
		List<User> findUserList = entityManager.createQuery("select u from User as u", User.class).getResultList();
		return findUserList;
	}

	@Override
	public void deleteUser(String deleteUser) {
		User findDeleteUser = entityManager.find(User.class, deleteUser);
		entityManager.remove(findDeleteUser);
	}
}