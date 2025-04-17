package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.model.SWE;
import com.tnite.jobwinner.service.SWEService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swe")
public class SWEController {

    @Autowired
    private SWEService sweService;

    @PostMapping("/create")
    public boolean createSWE(@RequestBody SWE swe) {
        return sweService.save(swe);
    }

    @GetMapping("/delete/{id}")
    public boolean deleteSWE(@PathVariable UUID id) {
        return sweService.removeById(id);
    }

    @PostMapping("/update")
    public boolean updateSWE(@RequestBody SWE swe) {
        return sweService.updateById(swe);
    }

    @GetMapping("/{id}")
    public SWE getSWEById(@PathVariable UUID id) {
        return sweService.getById(id);
    }

    @PostMapping("/list")
    public List<SWE> listSWEs(@RequestBody SWE swe) {
        return sweService.list(swe);
    }
}