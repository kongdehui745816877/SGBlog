package com.kong.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    //@Scheduled(cron = "0/5 * * * * ?")
    public void testJob(){
        //执行的代码
        System.out.println("定时任务执行了");
    }
}
