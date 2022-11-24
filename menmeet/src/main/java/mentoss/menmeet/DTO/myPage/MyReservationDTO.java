package mentoss.menmeet.DTO.myPage;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MyReservationDTO {
	private Integer reservationNum;
	private Integer postNum;
	private Integer category;
	private String mentorId;
	private String menteeId;
	private String title;
	private LocalDateTime time;
}
