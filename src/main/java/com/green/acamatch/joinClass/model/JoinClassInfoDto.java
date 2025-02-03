package com.green.acamatch.joinClass.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinClassInfoDto {
    @Schema(title = "강좌 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long classId;
    @Schema(title = "수업 이름", example = "초등 영어")
    private String className;
    @Schema(title = "수업 시작 날짜", example = "2025-01-16")
    private String startDate;
    @Schema(title = "수업 종료 날짜", example = "2025-01-30")
    private String endDate;
}