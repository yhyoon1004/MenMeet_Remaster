package mentoss.menmeet.DTO.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostContentDTO {
	private Integer postNum;
	private String writerName;
	private Integer category;/* 0 = 전공 / 1 = 진로 / 2 = 연애 / 3 = 학교생활 / 4 = 기타*/
	private Integer postIsMentor;	/*	0 = 멘티구인 1 = 멘토구인	 */
	private String title;
	private String content;
	private Integer mentoringEnable;	/*	0 = 불가능 1 = 가능	 */
	private LocalDateTime mentoringTime;
	private LocalDateTime postWriteTime;
	}
