package mentoss.menmeet.service;

import mentoss.menmeet.DTO.login.LoginFormDTO;
import mentoss.menmeet.DTO.login.LoginStateDTO;

public interface LoginService {
	LoginStateDTO confirmLogin(LoginFormDTO targetUser);
}
