package mentoss.menmeet.DTO.myPage;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyMentoringPostIndexDTO {
	private Integer postNum;
	private Integer isMentorPost;
	private String title;
	private LocalDateTime writeTime;
}
