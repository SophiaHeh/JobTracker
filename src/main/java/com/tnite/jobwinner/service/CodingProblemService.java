package com.tnite.jobwinner.service;

import com.tnite.jobwinner.model.CodingProblem;

import java.util.List;
import java.util.UUID;

public interface CodingProblemService {
    boolean save(CodingProblem codingProblem);

    boolean removeById(UUID id);

    List<CodingProblem> listByJobId(UUID jobId);
}
