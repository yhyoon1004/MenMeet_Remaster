package mentoss.menmeet.DTO.post;

import lombok.Getter;

@Getter
public class PostDeleteStateDTO {
	private Boolean isDeleted;

	public PostDeleteStateDTO(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
