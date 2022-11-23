package mentoss.menmeet.DTO.reservation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationApplicationFormDTO {
	private Integer postNum;
	private String applicant;
	private LocalDateTime applyTime;
}
