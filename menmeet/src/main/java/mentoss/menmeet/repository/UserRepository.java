package mentoss.menmeet.repository;

import mentoss.menmeet.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	public void saveUser(User saveUser);

	public Optional<User> findUserById(String targetUserId);

	public Optional<User> findUserByName(String targetUserName);
	public List<User> findUserAll();

	public void deleteUser(String deleteUser);
}
