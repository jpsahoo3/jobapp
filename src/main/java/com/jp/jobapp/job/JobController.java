package com.jp.jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

//    @PostMapping("/jobs")
//    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

//    @GetMapping("/jobs")
//    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Job>> getAllJob() {
        return  ResponseEntity.ok(jobService.findAll());
    }

//    @GetMapping("/jobs/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.findJobById(id);
        if(job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/jobs/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJobById(id, updatedJob);
        if(updated) {
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/jobs/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if(deleted) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
