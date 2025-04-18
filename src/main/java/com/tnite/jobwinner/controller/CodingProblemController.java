package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.model.CodingProblem;
import com.tnite.jobwinner.service.CodingProblemService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/codingproblem")
public class CodingProblemController {

    @Autowired
    private CodingProblemService codingProblemService;

    @PostMapping("/create")
    public ResponseEntity<CodingProblem> createCodingProblem(@RequestBody CodingProblem codingProblem) {
        CodingProblem savedProblem = codingProblemService.save(codingProblem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProblem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodingProblem(@PathVariable UUID id) {
        codingProblemService.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CodingProblem>> listByJobId(@RequestParam("jobId") UUID jobId) {
        List<CodingProblem> problems = codingProblemService.listByJobId(jobId);
        return ResponseEntity.ok(problems);
    }

//    @GetMapping("/delete/{id}")
//    public boolean deleteCodingProblem(@PathVariable UUID id) {
//        return codingProblemService.removeById(id);
//    }
//
//
//    @GetMapping("/list/jobId")
//    public List<CodingProblem> listByJobId(@RequestParam("jobId") UUID jobId) {
//        return codingProblemService.listByJobId(jobId);
//    }
}