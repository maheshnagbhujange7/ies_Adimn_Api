package com.spider.ADMIN_API.repositires;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spider.ADMIN_API.entities.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {

}
