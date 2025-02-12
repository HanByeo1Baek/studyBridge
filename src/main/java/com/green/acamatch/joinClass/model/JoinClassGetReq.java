package com.green.acamatch.joinClass.model;

import com.green.acamatch.config.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinClassGetReq extends Paging {
    @Schema(title = "사용자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    public JoinClassGetReq(Integer page, Integer size, Long userId) {
        super(page, size);
        this.userId = userId;
    }
}