package com.ats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.entity.Tender;

public interface TenderRepository extends JpaRepository<Tender, Integer> {

}
