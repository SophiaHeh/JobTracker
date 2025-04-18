package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.model.CodingProblem;
import com.tnite.jobwinner.model.SWE;
import com.tnite.jobwinner.service.SWEService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/swe")
//public class SWEController {
//
//    @Autowired
//    private SWEService sweService;
//
//    @PostMapping("/create")
//    public SWE createSWE(@RequestBody SWE swe) {
//        return sweService.save(swe);
//    }
//
//    @GetMapping("/delete/{id}")
//    public boolean deleteSWE(@PathVariable UUID id) {
//        return sweService.removeById(id);
//    }
//
//    @PostMapping("/update")
//    public boolean updateSWE(@RequestBody SWE swe) {
//        return sweService.updateById(swe);
//    }
//
//    @GetMapping("/{id}")
//    public SWE getSWEById(@PathVariable UUID id) {
//        return sweService.getById(id);
//    }
//
//    @PostMapping("/list")
//    public List<SWE> listSWEs(@RequestBody SWE swe) {
//        return sweService.list(swe);
//    }
//}




@RestController
@RequestMapping("/swe")
public class SWEController {

    @Autowired
    private SWEService sweService;

    @PostMapping("/create")
    public ResponseEntity<SWE> createSWE(@RequestBody SWE swe) {
        // Set job reference for each CodingProblem to avoid null job_id
        if (swe.getCodingProblems() != null) {
            for (CodingProblem problem : swe.getCodingProblems()) {
                problem.setJob(swe);
            }
        }
        SWE createdJob = sweService.save(swe);
        System.out.println("Created SWE with ID: " + createdJob.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }


    @GetMapping
    public ResponseEntity<List<SWE>> listAllJobs(SWE swe) {
        List<SWE> jobs = sweService.list(swe);
        return ResponseEntity.ok(jobs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@RequestBody SWE updatedJob) {
        boolean updated = sweService.updateById(updatedJob);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id) {
        boolean deleted = sweService.removeById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<SWE>> listByConditions(@RequestBody SWE sweParam) {
        List<SWE> jobs = sweService.list(sweParam);
        return ResponseEntity.ok(jobs);
    }
}