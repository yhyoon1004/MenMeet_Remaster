package mentoss.menmeet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
	@Id
	@Column(name = "id",length = 20)
	private String userId;
	@Column(name = "password",length = 20)
	private String userPassword;
	@Column(name = "name", unique = true, length = 10)
	private String userName;
	@Column(name = "administrator")
	private byte admin = 0;


}
