package mentoss.menmeet.repository;

import mentoss.menmeet.entity.MentoringPost;
import mentoss.menmeet.entity.Reservation;
import mentoss.menmeet.entity.ReservationSubscription;
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

	//멘토링 신청
	public void saveReservationSubscriptionApply(ReservationSubscription reservationSubscription) {
		entityManager.persist(reservationSubscription);
	}

	//멘토링 수락
	public void acceptMentoring(Integer subscriptionNum) {
		ReservationSubscription targetSubscription = entityManager.find(ReservationSubscription.class, subscriptionNum);
		targetSubscription.setIsAccept(1);
		MentoringPost posts = entityManager.find(MentoringPost.class, targetSubscription.getPostNum());
		String mentor;
		String mentee;
		if (posts.getMentoringTarget() == 0) {//멘티 구인이면
			mentor = posts.getWriterId();
			mentee = targetSubscription.getApplicant();
		} else {//멘토구인이면
			mentor = targetSubscription.getApplicant();
			mentee = posts.getWriterId();
		}
		Reservation newReservation = new Reservation();
		newReservation.setMentoringPostNum(posts.getPostNum());
		newReservation.setMentor(mentor);
		newReservation.setMentee(mentee);
		newReservation.setMentoringTime(posts.getMentoringTime());
		newReservation.setCategory(posts.getCategory());
		newReservation.setReservationState(1);
		newReservation.setMentoringOwner(posts.getWriterId());

		entityManager.persist(newReservation);
		posts.setMentoringEnable(1);
		entityManager.flush();
	}

	//멘토링 신청 취소
	public void deleteMentoringRequest(Integer requestNum) {
		ReservationSubscription rsTarget = entityManager.find(ReservationSubscription.class, requestNum);
		entityManager.remove(rsTarget);
		entityManager.flush();
	}

	//멘토링 거절
	public void rejectMentoring(Integer subscriptionNum) {
		ReservationSubscription rsEntity = entityManager.find(ReservationSubscription.class, subscriptionNum);
//		entityManager.remove(rsEntity);
		rsEntity.setIsAccept(2);
		entityManager.flush();
	}

	//내가 신청한 멘토링 조회
	public List<ReservationSubscription> showMyApplyMentoringList(String userId) {
		List<ReservationSubscription> MyMentoringApplyList = entityManager
				.createQuery("SELECT RS FROM ReservationSubscription  RS "
						+ "WHERE RS.applicant=:USERID", ReservationSubscription.class)
				.setParameter("USERID", userId).getResultList();
		return MyMentoringApplyList;
	}

	//신청 받은 멘토링 조회
	public List<ReservationSubscription> showReceivedMentoringRequest(Integer postNum) {
		List<ReservationSubscription> resultRSList = entityManager
				.createQuery("SELECT RS FROM ReservationSubscription RS WHERE RS.postNum=:targetNum", ReservationSubscription.class)
				.setParameter("targetNum", postNum)
				.getResultList();

		return resultRSList;
	}

	//해당 id의 예약 정보 조회
	public List<Reservation> searchReservationByUserId(String userId) {
		List<Reservation> resultReservationList = entityManager
				.createQuery("SELECT R FROM Reservation R WHERE R.mentee=:userId OR R.mentor=:userId", Reservation.class)
				.setParameter("userId", userId)
				.getResultList();
		return resultReservationList;
	}


}
