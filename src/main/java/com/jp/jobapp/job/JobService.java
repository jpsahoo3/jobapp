package com.jp.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job findJobById(Long id);
    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
