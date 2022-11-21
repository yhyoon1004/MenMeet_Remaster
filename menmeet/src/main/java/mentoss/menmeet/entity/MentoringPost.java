package mentoss.menmeet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedStoredProcedureQuery(
		name = "postListProcedure",
		procedureName = "showPostList",
		resultClasses = MentoringPost.class,
		parameters = {
				@StoredProcedureParameter(name = "_category",type = Integer.class,mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "_isMentor",type = Integer.class,mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "_keyword",type = String.class,mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "_pageNum",type = Integer.class,mode = ParameterMode.IN)
		})
@NamedStoredProcedureQuery(
		name = "postCountProcedure",
		resultClasses = PostCount.class,
		procedureName = "showPostCount",
		parameters = {
				@StoredProcedureParameter(name = "_category",type = Integer.class,mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "_isMentor",type = Integer.class,mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "_keyword",type = String.class,mode = ParameterMode.IN)
		})
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
	private Integer mentoringTarget;//0멘티/1멘토
	@Column(length = 20)
	private String title;
	@Column(length = 300)
	private String content;
	@Column(name = "mentoring_enable")
	private Integer mentoringEnable;
	@Column(name = "posting_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime postingTime;
	@Column(name = "reservation_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime mentoringTime;
}
