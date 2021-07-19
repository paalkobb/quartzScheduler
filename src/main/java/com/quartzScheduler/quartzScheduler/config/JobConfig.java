package com.quartzScheduler.quartzScheduler.config;

import com.quartzScheduler.quartzScheduler.model.SampleJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

public class JobConfig {


    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob().ofType(SampleJob.class)
                .storeDurably()
                .withIdentity("Quartz_job_detail")
                .withDescription("Invoke sample job serive")
                .build();
    }

    @Bean
    public JobDetailFactoryBean jobDetail() {

    }

}
