package com.example.jeju_aws.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String address;

    private String phone;
    private String operatingHours;
    private String description;
    private Double latitude;
    private Double longitude;
    private String keywords;
    private String imageUrl;
    private String homepageLink;  // 홈페이지 링크
    private String productName1;  // 판매 상품 1 이름
    private BigDecimal productPrice1;  // 판매 상품 1 가격
    private String productName2;  // 판매 상품 2 이름
    private BigDecimal productPrice2;  // 판매 상품 2 가격
    private Boolean favoriteYn;  // boolean으로 변경
    private Boolean activeYn;    // boolean으로 변경
}
