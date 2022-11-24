package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.reservation.*;
import mentoss.menmeet.entity.Reservation;
import mentoss.menmeet.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {
	private final ReservationService reservationService;

	//멘토링 신청
	@PostMapping(value = "/mentoringApplication", consumes = "application/json")
	public ReservationApplicationStateDTO  mentoringApplication(@RequestBody ReservationApplicationFormDTO rfdto){
		return reservationService.requestReservation(rfdto);
	}
	//멘토링 신청 취소
	@DeleteMapping(value = "/mentoringCancel/{applicationNum}")
	public MentoringApplyCancelStateDTO cancelMentoringApply(@PathVariable Integer applicationNum){
		return reservationService.cancelMentoringApply(applicationNum);
	}

	//멘토링 수락
	@PostMapping(value = "/acceptMentoring", consumes = "application/json")
	public MentoringAcceptStateDTO acceptMentoringRequest(@RequestBody MentoringRequestDTO mentoringRequest){
		return reservationService.acceptMentoring(mentoringRequest);
	}

	//멘토링 거절
	@GetMapping( "/rejectMentoring/{subscriptionNum}")
	public MentoringRejectStateDTO rejectMentoringRequest(@PathVariable Integer subscriptionNum){
			MentoringRejectStateDTO mrDTO = new MentoringRejectStateDTO();
			reservationService.rejectMentoring(subscriptionNum);
			mrDTO.setIsRejected(true);
		return mrDTO;
	}

}
