package com.ats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.entity.Resgistration;

public interface RegistrationRepository extends JpaRepository<Resgistration, String> {
	 boolean existsByNameAndPassword(String name, String password);
}
