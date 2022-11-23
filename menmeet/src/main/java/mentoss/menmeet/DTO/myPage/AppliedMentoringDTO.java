package mentoss.menmeet.DTO.myPage;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppliedMentoringDTO {
	private Integer subscriptNum;
	private Integer postNum;
	private Boolean isMentor;
	private String postTitle;
	private LocalDateTime applyTime;
	private Integer state;//0=대기중, 1= 수락함, 2= 거절함
}
