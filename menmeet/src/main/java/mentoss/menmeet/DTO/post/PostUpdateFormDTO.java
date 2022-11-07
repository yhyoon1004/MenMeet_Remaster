package mentoss.menmeet.DTO.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostUpdateFormDTO {
	private Integer postNum;
	private String userId;
	private String title;
	private String content;
	private Integer isMentor;
	private LocalDateTime mentoringTime;
}
