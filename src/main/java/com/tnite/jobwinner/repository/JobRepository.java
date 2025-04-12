package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.GeneralJob;
import com.tnite.jobwinner.model.Job;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JobRepository extends JpaRepository<GeneralJob, UUID> {
}