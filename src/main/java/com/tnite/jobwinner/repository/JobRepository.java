package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.GeneralJob;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<GeneralJob, UUID> {
}