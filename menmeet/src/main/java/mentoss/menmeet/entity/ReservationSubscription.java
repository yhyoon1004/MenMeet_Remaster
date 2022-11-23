package mentoss.menmeet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reservation_subscription")
public class ReservationSubscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_num")
	private Integer num;
	@Column(name = "mentoring_post_num")
	private Integer postNum;
	@Column(name = "applicant_id")
	private String applicant;
	@Column(name = "application_time")
	private LocalDateTime requestTime;
	@Column(name = "accept")
	private Integer isAccept;
}
