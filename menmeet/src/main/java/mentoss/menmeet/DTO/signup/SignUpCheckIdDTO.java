package mentoss.menmeet.DTO.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpCheckIdDTO {
	private String targetId;
	private Boolean isDuplicated;
}
