package mentoss.menmeet.DTO.post;

import lombok.Getter;

@Getter
public class PostCreateStateDTO {
	private Boolean isCreated;

	public PostCreateStateDTO(Boolean isCreated) {
		this.isCreated = isCreated;
	}
}
