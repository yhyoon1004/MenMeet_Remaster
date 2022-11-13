package mentoss.menmeet.DTO.post;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
public class PostContentDTO {
	private Integer postNum;
	private String writerName;
	private Integer category;/* 0 = 전공 / 1 = 진로 / 2 = 연애 / 3 = 학교생활 / 4 = 기타*/
	private Boolean postIsMentor;	/*	0 = 멘티구인 1 = 멘토구인	 */
	private String title;
	private String content;
	private Boolean mentoringEnable;
	private LocalDateTime mentoringTime;
	private LocalDateTime postWriteTime;
}
