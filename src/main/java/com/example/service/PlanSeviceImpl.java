package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Plan;
import com.example.entity.PlanCategory;
import com.example.repository.PlanCategoryRepo;
import com.example.repository.PlanRepo;

@Service
public class PlanSeviceImpl implements PlanSevice{
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	
	@Autowired
	private PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categoryList=planCategoryRepo.findAll();
		Map<Integer,String> map=new HashMap<>();
		categoryList.forEach(cat -> {
			map.put(cat.getCategoryId(),cat.getCategoryName());
			
		});
		
		return map;
	}

	@Override
	public boolean save(Plan plan) {
		Plan plan1=planRepo.save(plan);
		return plan1.getPlanId()!=null;
	}

	@Override
	public Plan getPlanId(Integer PlanId) {
		Optional<Plan> findBy=planRepo.findById(PlanId);
		if(findBy.isPresent()) {
			return findBy.get();
		}
		return null;
	}

	@Override
	public boolean update(Plan plan) {
		Plan plan1=planRepo.save(plan);
		return plan1.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status=false;
		try {
		planRepo.deleteById(planId);
		status=true;
		}catch(Exception ex) {
			ex.printStackTrace();			
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer Id, String activeSw) {
		Optional<Plan> findBy=planRepo.findById(Id);
		if(findBy.isPresent()) {
			Plan plan=findBy.get();
			plan.setActiveSw(activeSw);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan>listOfPlans=planRepo.findAll();
		return listOfPlans;
	}

}
