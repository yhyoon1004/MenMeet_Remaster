package mentoss.menmeet.DTO.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpCheckNameDTO {
	private String targetName;
	private Boolean isDuplicated;
}
