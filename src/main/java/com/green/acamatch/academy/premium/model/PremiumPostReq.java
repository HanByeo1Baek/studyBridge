package com.green.acamatch.academy.premium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.acamatch.entity.academy.Academy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PremiumPostReq {
    @Schema(title = "신청한학원pk")
    private Long acaId;

    @JsonIgnore
    @Schema(title = "프리미엄 시작일")
    private LocalDate startDate;

    @JsonIgnore
    @Schema(title = "프리미엄 종료일")
    private LocalDate endDate;

    @JsonIgnore
    @Schema(title = "프리미엄 승인여부", example = "0", description = "0(승인대기), 1(승인완료), 2(수정)")
    private int preCheck;

    @Schema(title = "프리미엄 가격")
    private int price;

    public void setAcaId(Academy academyId) {
        this.acaId = academyId.getAcaId();
    }
}
