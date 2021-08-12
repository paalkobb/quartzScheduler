package com.quartzScheduler.quartzScheduler.quartz1.exampleImpl;

import com.quartzScheduler.quartzScheduler.quartz1.info.TimerInfo;
import com.quartzScheduler.quartzScheduler.quartz1.job.SampleJob;
import com.quartzScheduler.quartzScheduler.quartz1.timerService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaygroundService {

    private final SchedulerService schedulerService;

    @Autowired
    public PlaygroundService(final SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    public void runSampleJob(){
        TimerInfo timerInfo = new TimerInfo();
        timerInfo.setTotalFireCount(5);
        timerInfo.setRunForever(false);
        timerInfo.setInitialOffset(0);
        timerInfo.setCallbackData(null);
        timerInfo.setRepeatInterval(500);

        schedulerService.schedule(SampleJob.class, timerInfo);

    }

}
