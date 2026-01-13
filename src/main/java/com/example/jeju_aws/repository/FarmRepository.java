package com.example.jeju_aws.repository;

import com.example.jeju_aws.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
