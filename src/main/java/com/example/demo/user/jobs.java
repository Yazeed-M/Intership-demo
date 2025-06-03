package com.example.demo.user;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class jobs {

    @Autowired
    private final JobScheduler jobScheduler;
    @Autowired
    private final UserService userService;

    public jobs(JobScheduler jobScheduler, UserService userService) {
        this.jobScheduler = jobScheduler;
        this.userService = userService;
    }

    @Job
    public void scheduling(String name) {
        jobScheduler.enqueue(() -> userService.getUserByname(name));
    }

    @Job
    public void sched() {

        jobScheduler.scheduleRecurrently("recurringJobBuilder", Cron.hourly(), () -> userService.sch("an hour has passed"));
    }
}
