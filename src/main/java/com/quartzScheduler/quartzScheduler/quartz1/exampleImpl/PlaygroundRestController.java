package com.quartzScheduler.quartzScheduler.quartz1.exampleImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/samplejob")
public class PlaygroundRestController {

    private final PlaygroundService playgroundService;

    @Autowired
    public PlaygroundRestController(PlaygroundService playgroundService){
        this.playgroundService = playgroundService;
    }


    @GetMapping("/job")
    public void runSampleJob(){
        playgroundService.runSampleJob();
    }

}
