package mentoss.menmeet.DTO.login;

import lombok.Getter;

@Getter
public class LogoutStateDTO {
	public LogoutStateDTO(Boolean isLogout) {
		this.isLogout = isLogout;
	}

	private Boolean isLogout;
}
