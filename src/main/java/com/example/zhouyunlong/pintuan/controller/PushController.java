package com.example.zhouyunlong.pintuan.controller;

import com.example.zhouyunlong.pintuan.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/23 17:47
 * 4
 */
@Slf4j
@RestController
@RequestMapping("/wx/notice/{appid}")
public class PushController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "pushJob")
    private Job pushJob;

    public JobParameters jobParameters;
    @GetMapping("/push")
    public CommonResult login(@PathVariable String appid) {
        jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis())
                .addString("name","推送开始")
                .toJobParameters();

        try {
            jobLauncher.run(pushJob,jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

        return CommonResult.ok("ok");
    }
}
