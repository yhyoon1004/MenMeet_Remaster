package mentoss.menmeet.repository;

import mentoss.menmeet.entity.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ReservationRepository {

	@PersistenceContext
	private EntityManager entityManager;

	//멘토링 수락

	//멘토링 취소

	//내가 신청한 멘토링 조회

	//신청 받은 멘토링 조회


	//해당 id의 예약 정보 조회
	public List<Reservation> searchReservationByUserId(String userId){

		List<Reservation> searchedList = new ArrayList<>();
		return searchedList;
	}


}
