package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.reservation.ReservationApplicationFormDTO;
import mentoss.menmeet.DTO.reservation.ReservationApplicationStateDTO;
import mentoss.menmeet.entity.Reservation;
import mentoss.menmeet.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/reservation")
@RequiredArgsConstructor
public class ReservationController {
	private final ReservationService reservationService;

	//예약신청
	@PostMapping(value = "/mentoringApplication", consumes = "application/json")
	public ReservationApplicationStateDTO  mentoringApplication(@RequestBody ReservationApplicationFormDTO rfdto){
		return null;
	}
}
