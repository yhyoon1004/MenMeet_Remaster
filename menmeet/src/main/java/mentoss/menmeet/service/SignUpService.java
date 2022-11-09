package mentoss.menmeet.service;

import mentoss.menmeet.DTO.signup.SignUpCheckIdDTO;
import mentoss.menmeet.DTO.signup.SignUpCheckNameDTO;
import mentoss.menmeet.DTO.signup.SignUpConfirmDTO;
import mentoss.menmeet.entity.User;

import java.util.List;

public interface SignUpService {

	SignUpConfirmDTO signUp(User targetUser);

	SignUpCheckIdDTO checkId(String targetId);

	SignUpCheckNameDTO checkName(String targetName);

	List<User> showUserList();
}
