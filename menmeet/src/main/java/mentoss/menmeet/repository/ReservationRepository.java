package mentoss.menmeet.repository;

import mentoss.menmeet.entity.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ReservationRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void saveReservation(Reservation reservation){
		entityManager.persist(reservation);
	}
}
