package com.example.jeju_aws.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Data;

@Entity
@Data
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String operatingHours;
    private String description;
    private Double latitude;
    private Double longitude;
    private String keywords;
    private Boolean favoriteYn;
    private Boolean activeYn;  // 게시글 활성화 여부 추가
    private String imageUrl;

    // 추가된 필드
    private String homepageLink;  // 홈페이지 링크
    private String productName1;  // 판매 상품 1 이름
    private BigDecimal productPrice1;  // 판매 상품 1 가격
    private String productName2;  // 판매 상품 2 이름
    private BigDecimal productPrice2;  // 판매 상품 2 가격

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
