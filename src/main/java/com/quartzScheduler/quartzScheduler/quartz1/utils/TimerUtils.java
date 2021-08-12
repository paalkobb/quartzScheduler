package com.quartzScheduler.quartzScheduler.quartz1.utils;


import com.quartzScheduler.quartzScheduler.quartz1.info.TimerInfo;
import org.quartz.*;

import java.util.Date;

public class TimerUtils {

    private TimerUtils(){}

    public static JobDetail buildJobDetail(final Class jobClass, final TimerInfo info){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }


    public static Trigger buildTrigger(final Class jobClass, final TimerInfo info){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.getRepeatInterval());
        builder = repeatForever(info, builder);

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis()+ info.getInitialOffset()))
                .build();
    }


    private static SimpleScheduleBuilder repeatForever(TimerInfo info, SimpleScheduleBuilder builder){
        if(info.isRunForever()){
            builder.repeatForever();
            return builder;
        }
        builder = builder.withRepeatCount(info.getTotalFireCount());
        return builder;
    }

}
