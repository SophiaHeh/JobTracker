package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.SWE;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SWERepository extends JpaRepository<SWE, UUID> {
}