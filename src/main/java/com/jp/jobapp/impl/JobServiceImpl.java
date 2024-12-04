package com.jp.jobapp.impl;

import com.jp.jobapp.job.JobRepository;
import com.jp.jobapp.job.JobService;
import com.jp.jobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
//    private long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
//        return jobs;
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) {
//                return job;
//            }
//        }
//        return null;
        return  jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()) {
//            Job job = iterator.next();
//            if (job.getId().equals(id)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            if (jobRepository.existsById(id)) { // Check if the job exists
                jobRepository.deleteById(id);
                return true; // Return true only if deletion was successful
            } else {
                return false; // Return false if the job does not exist
            }
        } catch (Exception e) {
            return false; // Handle any exceptions that may occur
        }

    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) {
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setLocation(updatedJob.getLocation());
//
//                return true;
//            }
//        }
//        return false;
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
