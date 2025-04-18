package com.tnite.jobwinner.service.impl;

import com.tnite.jobwinner.excpetion.NotFoundException;
import com.tnite.jobwinner.model.CodingProblem;
import com.tnite.jobwinner.model.SWE;
import com.tnite.jobwinner.repository.CodingProblemRepository;
import com.tnite.jobwinner.repository.SWERepository;
import com.tnite.jobwinner.service.CodingProblemService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CodingProblemServiceImpl implements CodingProblemService {
    private final CodingProblemRepository codingProblemRepository;
    private final SWERepository sweRepository;

    @Autowired
    public CodingProblemServiceImpl(CodingProblemRepository codingProblemRepository, SWERepository sweRepository){
        this.codingProblemRepository = codingProblemRepository;
        this.sweRepository = sweRepository;
    }

    // The data of the question list is placed in sweServiceImpl
    @Override
    public CodingProblem save(CodingProblem codingProblem) {
        return codingProblemRepository.save(codingProblem);
    }

    @Override
    public List<CodingProblem> listByJobId(UUID jobId) {
        if (jobId == null) {
            throw new IllegalArgumentException("JobId should not be null.");
        }
        SWE job = sweRepository.findById(jobId)
            .orElseThrow(() -> new NotFoundException("SWE job with ID " + jobId + " not found."));
        return codingProblemRepository.findByJobId(jobId);
    }

    @Override
    public boolean removeById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null.");
        }
        if (!codingProblemRepository.existsById(id)) {
            throw new NotFoundException("CodingProblem with ID " + id + " not found.");
        }
        codingProblemRepository.deleteById(id);
        return true;
    }


}
