package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.entity.Reservation;
import mentoss.menmeet.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationRepository reservationRepository;
	public void enrollReservation(){
		Reservation reservation = new Reservation();
		reservation.setMentoringPostNum(22);
		reservation.setMentor("syh2516");
		reservation.setCategory(3);
		reservation.setMentoringTime(LocalDateTime.of(2022,11,15,23,10,12,000));
		reservation.setReservationState(0);
		reservation.setReservationAccept(0);
		reservationRepository.saveReservation(reservation);
	}
}
