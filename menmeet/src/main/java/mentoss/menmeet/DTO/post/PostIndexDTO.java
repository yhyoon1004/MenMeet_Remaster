package mentoss.menmeet.DTO.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostIndexDTO {

	private String postTitle;
	private String postWriter;
	private LocalDateTime mentoringDate;
	private Boolean mentoringEnable;
	private LocalDateTime postWriteTime;
}
