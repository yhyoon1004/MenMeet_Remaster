package mentoss.menmeet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "mentoring_post")
public class MentoringPost {
	@Id
	@Column(name = "post_num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postNum;
	@Column(name = "writer_id", length = 20)
	private String writerId;
	@Column(name = "writer_name",length = 10)
	private String writerName;
	@Column
	private Integer category;
	@Column(name = "mentoring_target")
	private Integer mentoringTarget;
	@Column(length = 20)
	private String title;
	@Column(length = 300)
	private String content;
	@Column(name = "mentoring_enable")
	private Integer mentoringEnable;
	@Column(name = "posting_time")
	private LocalDateTime postingTime;
	@Column(name = "reservation_time")
	private LocalDateTime mentoringTime;
}
