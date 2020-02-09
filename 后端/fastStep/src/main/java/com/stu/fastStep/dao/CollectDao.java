package com.stu.fastStep.dao;

import java.util.List;

import com.stu.fastStep.domain.Collect;

public interface CollectDao {
	public Collect getCollectByUserAndCoupon(Collect collect);
	public List<Collect> getCollectsById(int userId);
	public int updateCollect(Collect collect);
	public Collect insertCollect(Collect collect);
}
