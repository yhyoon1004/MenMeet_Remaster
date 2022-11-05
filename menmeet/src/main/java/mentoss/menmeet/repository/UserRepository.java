package mentoss.menmeet.repository;

import mentoss.menmeet.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	void saveUser(User saveUser);

	Optional<User> findUserById(String targetUserId);

	Optional<User> findUserByName(String targetUserName);

	List<User> findUserAll();

	void deleteUser(String deleteUser);
}
