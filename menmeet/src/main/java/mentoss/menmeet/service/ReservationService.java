package mentoss.menmeet.service;

import lombok.RequiredArgsConstructor;
import mentoss.menmeet.DTO.reservation.*;
import mentoss.menmeet.entity.ReservationSubscription;
import mentoss.menmeet.entity.User;
import mentoss.menmeet.repository.ReservationRepository;
import mentoss.menmeet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final UserRepository userRepository;
	//멘토링 신청
	public ReservationApplicationStateDTO requestReservation( ReservationApplicationFormDTO rafDTO){
		ReservationSubscription rsEntity = new ReservationSubscription();
		rsEntity.setPostNum(rafDTO.getPostNum());
		rsEntity.setApplicant(rafDTO.getApplicant());
		rsEntity.setRequestTime(LocalDateTime.now());
		rsEntity.setIsAccept(0);
		reservationRepository.saveReservationSubscriptionApply(rsEntity);
		ReservationApplicationStateDTO rasDTO = new ReservationApplicationStateDTO();
		rasDTO.setIsApplicated(true);
		return rasDTO;
	}

	//멘토링 신청취소
	public MentoringApplyCancelStateDTO cancelMentoringApply(Integer applyNum){
		reservationRepository.deleteMentoringRequest(applyNum);
		MentoringApplyCancelStateDTO macsDTO = new MentoringApplyCancelStateDTO();
		macsDTO.setIsCanceled(true);
		return macsDTO;
	}


	//멘토링 수락
	public MentoringAcceptStateDTO acceptMentoring(MentoringRequestDTO mentoringRequestDTO){
		MentoringAcceptStateDTO masDTO = new MentoringAcceptStateDTO();

		Optional<User> userById = userRepository.findUserById(mentoringRequestDTO.getReceivedUserId());
		if(!userById.isPresent()){//해당 유저의 정보가 존재하지 않으면
			masDTO.setIsAccepted(false);
		}else {
			reservationRepository.acceptMentoring(mentoringRequestDTO.getRequestNum());
			masDTO.setIsAccepted(true);
		}
		return masDTO;
	}

	//멘토링 거절
	public void rejectMentoring(Integer mentoringRequestNum){
		reservationRepository.rejectMentoring(mentoringRequestNum);
	}

}
