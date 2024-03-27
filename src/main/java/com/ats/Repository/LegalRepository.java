package com.ats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.entity.Legal;

public interface LegalRepository extends JpaRepository<Legal, Integer>{
	public List<Legal> findBytenderid(int id);
}
