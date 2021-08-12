package com.quartzScheduler.quartzScheduler.quartz1.timerService;

import com.quartzScheduler.quartzScheduler.quartz1.info.TimerInfo;
import com.quartzScheduler.quartzScheduler.quartz1.job.SampleJob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class SchedulerServiceTest {

    @Autowired
    SchedulerService schedulerService;

    @Test
    void schedule() {
        TimerInfo timerInfo = new TimerInfo();
        timerInfo.setTotalFireCount(5);
        timerInfo.setRunForever(false);
        timerInfo.setInitialOffset(0);
        timerInfo.setCallbackData(null);
        timerInfo.setRepeatInterval(500);

        schedulerService.schedule(SampleJob.class, timerInfo);
    }
}