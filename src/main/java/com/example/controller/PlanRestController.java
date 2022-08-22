package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Plan;
import com.example.service.PlanSevice;


@RestController
public class PlanRestController {

	@Autowired
	private PlanSevice planSevice;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> map = planSevice.getPlanCategories();
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> save(@org.springframework.web.bind.annotation.RequestBody Plan plan) {
		String responseMsg = "";
		boolean isSaved = planSevice.save(plan);
		if (isSaved) {
			responseMsg = "Plan saved";
		} else {
			responseMsg = "Plan not saved";
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);

	}

	@GetMapping("/plan")
	public ResponseEntity<List<Plan>> getPlans() {
		List<Plan> list = planSevice.getAllPlans();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> edit(@PathVariable Integer planId){
		Plan plan=planSevice.getPlanId(planId);
		return new ResponseEntity<>(plan,HttpStatus.OK);
		
	}

	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> delete(@PathVariable Integer planId){
		String msg="";
		boolean isdeleted=planSevice.deletePlanById(planId);
		if(isdeleted) {
			msg="Plan deleted";
		}else {
			msg="Plan not deleted";
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
	}
	
	@PutMapping("/plan/{planId}")
	public ResponseEntity<String> updateu(@PathVariable Plan planId){
		String msg="";
		boolean isUpdated=planSevice.update(planId);
		if(isUpdated) {
			msg="Plan updated";
		}else {
			msg="Plan not updated";
		}
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
	}
	@PutMapping("/status-change/{planId}/{statusChange}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String statusChange){
		String msg="";
		
	boolean	planSevice1=planSevice.planStatusChange(planId, statusChange);
	if(planSevice1) {
		msg="status changed";
	}else {
		msg="status not changed";
	}
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
	}
}
