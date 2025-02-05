package com.green.acamatch.academy.model.HB;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private String comment;
    private double star;
    private long reviewId;
    private String createdAt;
    private long userId;
    private String nickName;
    private String className;
}