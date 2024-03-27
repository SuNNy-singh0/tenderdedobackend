package com.ats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ats.entity.Phase2;

public interface Phase2Repository extends JpaRepository<Phase2,Integer>{
	public List<Phase2> findBytenderid(int id);
}
