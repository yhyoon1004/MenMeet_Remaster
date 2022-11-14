package mentoss.menmeet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter@Setter
public class PostCount {
	@Id
	private Integer total_count;
}
