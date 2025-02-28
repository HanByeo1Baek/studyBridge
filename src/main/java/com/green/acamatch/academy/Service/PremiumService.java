package com.green.acamatch.academy.Service;

import com.green.acamatch.academy.AcademyRepository;
import com.green.acamatch.academy.PremiumRepository;
import com.green.acamatch.academy.model.JW.AcademyMessage;
import com.green.acamatch.academy.premium.model.PremiumDeleteReq;
import com.green.acamatch.academy.premium.model.PremiumGetRes;
import com.green.acamatch.academy.premium.model.PremiumPostReq;
import com.green.acamatch.academy.premium.model.PremiumUpdateReq;
import com.green.acamatch.entity.academy.PremiumAcademy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PremiumService {
    private final AcademyMessage academyMessage;
    private final PremiumRepository premiumRepository;
    private final AcademyRepository academyRepository;


    //프리미엄학원 신청(학원관계자)
    public int insPremium(PremiumPostReq req) {

        PremiumAcademy premiumAcademy = new PremiumAcademy();
        premiumAcademy.setAcaId(req.getAcaId());
        premiumAcademy.setStartDate(req.getStartDate());
        premiumAcademy.setEndDate(req.getEndDate());
        premiumAcademy.setPreCheck(req.getPreCheck());
        premiumAcademy.setPrice(req.getPrice());

        premiumRepository.save(premiumAcademy);
        academyMessage.setMessage("프리미엄학원을 신청하였습니다.");
        return 1;
    }

    //프리미엄학원 수정(관리자 승인)
    public int updPremium(PremiumUpdateReq req) {
        int result = premiumRepository.updateByAcaId(req.getAcaId(), req.getPreCheck());
        academyMessage.setMessage("프리미엄 학원이 승인되었습니다.");
        if(result == 1) {
            updateAcademyPremiumIfNeeded(req.getAcaId());
            premiumRepository.updateDateByAcaId(req.getAcaId(), LocalDate.now(), LocalDate.now().plusMonths(1));
        }
        return 1;
    }

    //프리미엄학원 수정(승인)했을때 academy테이블 프리미엄 수정 메소드.
    @Transactional
    public void updateAcademyPremiumIfNeeded(Long acaId) {
        // PremiumAcademy의 preCheck 값을 확인
        Optional<PremiumAcademy> premiumAcademyOpt = premiumRepository.findByAcademy_AcaId(acaId);

        if (premiumAcademyOpt.isPresent()) {
            PremiumAcademy premiumAcademy = premiumAcademyOpt.get();

            // preCheck가 1이면 Academy의 premium을 1로 설정
            if (premiumAcademy.getPreCheck() == 1) {
                academyRepository.updateAcademyPremiumByAcaId(acaId); // Academy 테이블에 업데이트
            }
        }
    }

    //프리미엄학원 조회(관리자)
    public List<PremiumGetRes> getPremium() {
        List<PremiumGetRes> resList = premiumRepository.findAllByPremium();
        academyMessage.setMessage("프리미엄학원을 조회하였습니다.");
        return resList;
    }


    //프리미엄학원 삭제(관리자)
    public int delPremium(PremiumDeleteReq req) {

    premiumRepository.deleteByAcaId(req.getAcaId());
    academyMessage.setMessage("프리미엄학원을 삭제하였습니다.");
    academyRepository.updateAcademyPremiumEndByAcaId(req.getAcaId());

    return 1;
    }

    //프리미엄 종료일이 지나면 삭제
    // 매일 자정에 실행되도록 설정 (주기적으로 실행)
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredPremiumAcademies() {
        LocalDate now = LocalDate.now();
        List<Long> acaIds = premiumRepository.findAcaIdsByEndDateBefore(now);
        premiumRepository.deletePremiumAcademyByEndDate(now);

        for(Long acaId : acaIds) {
            academyRepository.updateAcademyPremiumEndByAcaId(acaId);
        }
    }
}
