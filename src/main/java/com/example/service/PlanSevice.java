package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.entity.Plan;

public interface PlanSevice {
	public Map<Integer, String> getPlanCategories();
	public boolean save(Plan plan);
	public Plan getPlanId(Integer PlanId);	
	public boolean update(Plan plan);
	public boolean deletePlanById(Integer planId);
	public boolean planStatusChange(Integer Id,String activeSw);
	public List<Plan> getAllPlans();
	

}
