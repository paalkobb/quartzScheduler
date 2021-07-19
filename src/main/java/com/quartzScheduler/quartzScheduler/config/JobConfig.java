package com.quartzScheduler.quartzScheduler.config;

import com.quartzScheduler.quartzScheduler.model.SampleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.Properties;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='false'")
public class JobConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${frequencyInSec}")
    private int frequencyInSec;

    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void init(){
        logger.info("Hello from Quartz...");
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        logger.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobDetail jobDetail(){
        return newJob().ofType(SampleJob.class)
                .storeDurably()
                .withIdentity(JobKey.jobKey("Quartz_Job_Detail"))
                .withDescription("Invoke sample job serivce...")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job){
        logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);
        return newTrigger()
                .forJob(job)
                .withIdentity(TriggerKey.triggerKey("Quartz_Trigger"))
                .withDescription("Sample trigger")
                .withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec)
                .repeatForever())
                .build();
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        // Starting scheduler threads
        scheduler.start();
        return scheduler;
    }

}
