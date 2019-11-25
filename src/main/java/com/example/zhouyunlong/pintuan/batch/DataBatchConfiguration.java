package com.example.zhouyunlong.pintuan.batch;

import com.example.zhouyunlong.pintuan.batch.listener.JobListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/23 18:31
 * 4
 */
@Configuration
@EnableBatchProcessing
@Slf4j
@Order(100)
public class DataBatchConfiguration {
    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private JobListener jobListener;

    @Autowired
    @Qualifier("pushStep")
    private Step pushStep;
    @Autowired
    private JobRepository jobRepository;


    /**
     * 一个简单基础的Job通常由一个或者多个Step组成
     */
    @Bean(name = "pushJob")
    public Job dataHandleJob() {
        Job job=jobBuilderFactory.get("dataHandleJob").
                incrementer(new RunIdIncrementer()).

                //start是JOB执行的第一个step
                start(pushStep).
//                next(xxxStep()).
//                next(xxxStep()).
//                ...
                //设置了一个简单JobListener
        listener((JobExecutionListener) jobListener).
                build();

        return job;
    }




}
