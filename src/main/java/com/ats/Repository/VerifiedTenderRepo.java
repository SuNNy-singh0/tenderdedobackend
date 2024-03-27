package com.ats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.entity.VerifiedTender;

public interface VerifiedTenderRepo extends JpaRepository<VerifiedTender, Integer> {

}
