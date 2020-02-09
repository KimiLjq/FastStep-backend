package com.stu.fastStep.dao;

import java.util.List;

import com.stu.fastStep.domain.Step;

public interface StepDao {
	public Step getStepByUserAndCoupon(Step step);
	public List<Step> getStepsById(int id);
	public int updateStep(Step step);
	public Step insertStep(Step step);
	
	
}
