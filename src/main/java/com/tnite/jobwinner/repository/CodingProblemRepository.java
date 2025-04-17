package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.CodingProblem;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingProblemRepository extends JpaRepository<CodingProblem, UUID> {
    List<CodingProblem> findByJobId(UUID jobId);

}
