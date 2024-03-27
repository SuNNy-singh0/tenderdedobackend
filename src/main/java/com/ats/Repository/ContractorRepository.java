package com.ats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.entity.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
public List<Contractor> findBytenderid(int id);
}
