package com.green.acamatch.academyCost.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSettlementListRes {
    @Schema(title = "학원 PK")
    private Long acaId;

    @Schema(title = "학원 이름")
    private String acaName;

    @Schema(title = "학원 주소")
    private String address;

    @Schema(title = "학원 사진")
    private String acaPic;

    @Schema(title = "정산 가격")
    private Integer totalPrice;

    @Schema(title = "정산 상태", description = "0이면 정산 X, 1이면 정산 O")
    private Integer latestStatus;

    @Schema(title = "총 검색된 수")
    private int totalCount;

    @Schema(title = "결제 PK들")
    private String costIds;
}
