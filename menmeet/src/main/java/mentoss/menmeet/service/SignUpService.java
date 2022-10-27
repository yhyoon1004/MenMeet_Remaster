package mentoss.menmeet.service;

import mentoss.menmeet.DTO.signup.SignUpCheckIdDTO;
import mentoss.menmeet.DTO.signup.SignUpCheckNameDTO;
import mentoss.menmeet.DTO.signup.SignUpConfirmDTO;
import mentoss.menmeet.domain.User;

import java.util.List;

public interface SignUpService {

	public SignUpConfirmDTO signUp(User targetUser);
	public SignUpCheckIdDTO checkId(String targetId);
	public SignUpCheckNameDTO checkName(String targetName);

	public List<User> showUserList();
}
