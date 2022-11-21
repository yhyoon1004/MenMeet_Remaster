package mentoss.menmeet.DTO.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MentoringPostIndex {
	private Integer postNum;
	private Integer isMentorPost;
	private String title;
	private LocalDateTime writeTime;
}
