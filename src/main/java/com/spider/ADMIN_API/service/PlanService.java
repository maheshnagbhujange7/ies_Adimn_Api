package com.spider.ADMIN_API.service;

import java.util.List;

import com.spider.ADMIN_API.bindings.PlanForm;

public interface PlanService {

	public boolean createPlan(PlanForm planForm);
	
	public List<PlanForm> fetchPlans();
	
	public PlanForm getPlanById(Integer palnId);
	
	public String changePlanStatus(Integer planId ,String status);
	
}
