package mentoss.menmeet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_num")
	private Integer reservationNum;
	@Column(name = "mentoring_post_num")
	private Integer mentoringPostNum;
	@Column(name = "mentor")
	private String mentor;
	@Column (name = "mentee")
	private String mentee;
	@Column(name = "category")
	private Integer category;
	@Column(name = "time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime  mentoringTime;
	@Column(name = "state")
	private Integer reservationState;
	@Column(name = "owner")
	private String mentoringOwner;
}
