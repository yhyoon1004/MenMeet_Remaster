package mentoss.menmeet.DTO.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostIndexDTO {

	private Integer postNum;
	private String postTitle;
	private Integer category;
	private Integer postIsMentor;
	private String postWriterName;
	private LocalDateTime mentoringTime;
	private Boolean mentoringEnable;
	private LocalDateTime postWriteTime;
}
