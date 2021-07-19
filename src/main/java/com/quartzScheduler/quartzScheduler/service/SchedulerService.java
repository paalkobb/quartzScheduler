package com.quartzScheduler.quartzScheduler.service;

import com.quartzScheduler.quartzScheduler.model.SampleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Autowired
    SampleJob sampleJob;



}
