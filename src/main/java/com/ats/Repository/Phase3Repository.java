package com.ats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ats.entity.Phase3;

public interface Phase3Repository extends JpaRepository<Phase3, Integer>{
	public List<Phase3> findBytenderid(int id);
}
