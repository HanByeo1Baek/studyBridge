package com.green.acamatch.academy.Controller;

import com.green.acamatch.academy.Service.PremiumService;
import com.green.acamatch.academy.model.JW.AcademyMessage;
import com.green.acamatch.academy.premium.model.PremiumDeleteReq;
import com.green.acamatch.academy.premium.model.PremiumPostReq;
import com.green.acamatch.academy.premium.model.PremiumUpdateReq;
import com.green.acamatch.config.model.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("premium")
public class PremiumController {
    private final PremiumService premiumService;
    private final AcademyMessage academyMessage;

    //프리미엄학원신청
    @PostMapping
    public ResultResponse<Integer> postPremiumAcademy(@RequestBody PremiumPostReq req) {
        int result = premiumService.insPremium(req);
        return null;
    }

    //프리미엄승인
    @PutMapping
    @Operation(summary = "프리미엄 승인", description = "프리미엄 승인은 preCheck = 1 보내주세요.")
    public ResultResponse<Integer> updPremiumCheck(@RequestBody PremiumUpdateReq req) {
        int result = premiumService.updPremium(req);

        return ResultResponse.<Integer>builder()
                .resultMessage(academyMessage.getMessage())
                .resultData(result)
                .build();
    }

    //프리미엄학원삭제
    @DeleteMapping
    public ResultResponse<Integer> deletePremiumAcademy(@RequestBody PremiumDeleteReq req) {
        int result = premiumService.delPremium(req);
        return null;
    }
}
