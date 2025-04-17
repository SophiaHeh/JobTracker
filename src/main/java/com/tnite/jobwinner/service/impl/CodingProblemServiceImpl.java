package com.tnite.jobwinner.service.impl;

import com.tnite.jobwinner.model.CodingProblem;
import com.tnite.jobwinner.repository.CodingProblemRepository;
import com.tnite.jobwinner.service.CodingProblemService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CodingProblemServiceImpl implements CodingProblemService {
    private final CodingProblemRepository codingProblemRepository;

    @Autowired
    public CodingProblemServiceImpl(CodingProblemRepository codingProblemRepository){
        this.codingProblemRepository = codingProblemRepository;
    }

    // The data of the question list is placed in sweServiceImpl
    @Override
    public boolean save(CodingProblem codingProblem) {
        if (codingProblem.getJob() == null) {
            throw new IllegalArgumentException("JobId should not be null.");
        }

        if (codingProblem.getQuestion() == null || codingProblem.getQuestion().isEmpty()) {
            throw new IllegalArgumentException("Question should not be null.");
        }

        if (codingProblem.getPlatform() == null || codingProblem.getPlatform().isEmpty()) {
            throw new IllegalArgumentException("Platform should not be null.");
        }

        if (codingProblem.getDifficulty() == null || codingProblem.getDifficulty().isEmpty()) {
            throw new IllegalArgumentException("Difficulty should not be null.");
        }
        codingProblemRepository.save(codingProblem);
        return true;
    }

    @Override
    public boolean removeById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null.");
        }
        Optional<CodingProblem> codingProblem = codingProblemRepository.findById(id);
        if (codingProblem.isPresent()) {
            codingProblemRepository.delete(codingProblem.get());
            return true;
        }
        return false;
    }

    @Override
    public List<CodingProblem> listByJobId(UUID jobId) {
        if (jobId == null) {
            throw new IllegalArgumentException("JobId should not be null.");
        }
        return codingProblemRepository.findByJobId(jobId);
    }
}
