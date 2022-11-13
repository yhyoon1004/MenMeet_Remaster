package mentoss.menmeet.DTO.post;

import lombok.Data;

@Data
public class PostSearchDTO {
	private Integer category;
	private Integer mentor;
	private Integer pageNum;
	private String keyword;
}
