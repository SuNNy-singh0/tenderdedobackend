package com.ats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.entity.Backup;

public interface BackupRepository extends JpaRepository<Backup, Integer>{

}
