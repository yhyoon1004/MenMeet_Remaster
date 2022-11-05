package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoss.menmeet.DTO.signup.SignUpCheckIdDTO;
import mentoss.menmeet.DTO.signup.SignUpCheckNameDTO;
import mentoss.menmeet.DTO.signup.SignUpConfirmDTO;
import mentoss.menmeet.domain.User;
import mentoss.menmeet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SignUpServiceImpl implements SignUpService {
	private final UserRepository userRepository;

	//회원등록 메서드
	@Override
	public SignUpConfirmDTO signUp(User targetUser) {
		log.info("called : SignUpServiceImpl.signUp");

		SignUpConfirmDTO scDTO = new SignUpConfirmDTO();//리턴해줄
		Optional<User> checkIdUserOptional = userRepository.findUserById(targetUser.getUserId());
		Optional<User> checkNameUserOptional = userRepository.findUserByName(targetUser.getUserId());

		if (checkIdUserOptional.isPresent() || checkNameUserOptional.isPresent()) {//DB에 값이 있으면
			scDTO.setSignUpState(false);
			scDTO.setUserId(targetUser.getUserId());
		} else {
			userRepository.saveUser(targetUser);
			scDTO.setSignUpState(true);
			scDTO.setUserId(targetUser.getUserId());
		}
		return scDTO;
	}

	//회원가입시 Id 중복 확인
	@Override
	public SignUpCheckIdDTO checkId(String targetId) {
		log.info("called : SignUpServiceImpl.checkId");

		SignUpCheckIdDTO signUpCheckIdDTO = new SignUpCheckIdDTO();
		signUpCheckIdDTO.setTargetId(targetId);

		Optional<User> checkUserIdOptional = userRepository.findUserById(targetId);
		if (checkUserIdOptional.isPresent()) {
			signUpCheckIdDTO.setIsDuplicated(true);
		} else {
			signUpCheckIdDTO.setIsDuplicated(false);
		}
		return signUpCheckIdDTO;
	}

	//회원가입시 Name 중복 확인
	@Override
	public SignUpCheckNameDTO checkName(String targetName) {
		log.info("called : SignUpServiceImpl.checkName");

		SignUpCheckNameDTO signUpCheckNameDTO = new SignUpCheckNameDTO();
		signUpCheckNameDTO.setTargetName(targetName);

		Optional<User> checkUserNameOptional = userRepository.findUserByName(targetName);
		if (checkUserNameOptional.isPresent()) {
			signUpCheckNameDTO.setIsDuplicated(true);
		} else {
			signUpCheckNameDTO.setIsDuplicated(false);
		}
		return signUpCheckNameDTO;
	}

	@Override
	public List<User> showUserList() {
		log.info("called : SignUpServiceImpl.showUserList");
		return userRepository.findUserAll();
	}
}
