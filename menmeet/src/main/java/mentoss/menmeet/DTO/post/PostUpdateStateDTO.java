package mentoss.menmeet.DTO.post;

import lombok.Getter;

@Getter
public class PostUpdateStateDTO {
	private Boolean isUpdated;

	public PostUpdateStateDTO(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
}
