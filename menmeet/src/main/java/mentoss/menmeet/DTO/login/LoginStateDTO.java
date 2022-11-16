package mentoss.menmeet.DTO.login;

import lombok.Data;

@Data
public class LoginStateDTO {
	private Boolean isLoginConfirmed;
	private String userId;
	private String userName;
}
