package com.example.jeju_aws.controller;

import com.example.jeju_aws.dto.FarmRequestDto;
import com.example.jeju_aws.entity.Farm;
import com.example.jeju_aws.service.FarmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RestController
@RequestMapping("/api/farms")
@Tag(name = "Farm API", description = "농장 정보 관리 API")
@Slf4j
public class FarmController {

    @Autowired
    private FarmService farmService;

    // 농장 등록
    @Operation(summary = "농장 등록", description = "새로운 농장을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "농장 생성 성공",
                         content = @Content(schema = @Schema(implementation = Farm.class))),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청 데이터")
    })
    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody @Valid FarmRequestDto farmRequestDto) {
        log.info("farmRequestDto: {}", farmRequestDto);
        Farm createdFarm = farmService.createFarm(farmRequestDto);
        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }

    // 농장 상세 조회
    @Operation(summary = "농장 상세 조회", description = "ID를 기반으로 농장 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "농장 조회 성공",
                         content = @Content(schema = @Schema(implementation = Farm.class))),
            @ApiResponse(responseCode = "404", description = "농장 정보를 찾을 수 없음")
    })
    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
        Optional<Farm> farm = farmService.getFarmById(farmId);
        if (farm.isPresent()) {
            return new ResponseEntity<>(farm.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 즐겨찾기 상태 업데이트
    @Operation(summary = "즐겨찾기 상태 업데이트", description = "농장의 즐겨찾기 상태를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "즐겨찾기 상태 업데이트 성공"),
            @ApiResponse(responseCode = "404", description = "농장 정보를 찾을 수 없음")
    })
    @PostMapping("/{farmId}/favorite")
    public ResponseEntity<Void> updateFavoriteStatus(@PathVariable Long farmId, @RequestBody FarmRequestDto farmRequestDto) {
        boolean isUpdated = farmService.updateFavoriteStatus(farmId, farmRequestDto.getFavoriteYn());
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 농장 활성화 상태 업데이트
    @Operation(summary = "농장 활성화 상태 업데이트", description = "농장의 활성화 상태를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "활성화 상태 업데이트 성공"),
            @ApiResponse(responseCode = "404", description = "농장 정보를 찾을 수 없음")
    })
    @PostMapping("/{farmId}/active")
    public ResponseEntity<Void> updateActiveStatus(@PathVariable Long farmId, @RequestBody FarmRequestDto farmRequestDto) {
        boolean isUpdated = farmService.updateActiveStatus(farmId, farmRequestDto.getActiveYn());
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
