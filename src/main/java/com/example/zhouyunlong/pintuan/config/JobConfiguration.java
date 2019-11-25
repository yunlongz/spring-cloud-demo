package com.example.zhouyunlong.pintuan.config;
import	java.beans.Beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/21 21:23
 * 4
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class JobConfiguration {
    @Bean
    public JobRepository jobRepository(@Autowired DataSource dataSource,@Autowired PlatformTransactionManager dataSourceTransactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(dataSourceTransactionManager);
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        jobRepositoryFactoryBean.setTablePrefix("BATCH_");
        try {
            jobRepositoryFactoryBean.afterPropertiesSet();
            return jobRepositoryFactoryBean.getObject();
        } catch (Exception e) {
            log.error("JobRepository bean could not be initialized");
            throw new BatchConfigurationException(e);
        }
    }

    /**
     * JobBuilderFactory
     *
     * @param jobRepository JobRepository
     * @return JobBuilderFactory
     */
    @Bean
    JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository);
    }

    /**
     * StepBuilderFactory
     *
     * @param jobRepository                jobRepository
     * @param dataSourceTransactionManager dataSourceTransactionManager
     * @return stepBuilderFactory
     */
    @Bean
    StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, PlatformTransactionManager dataSourceTransactionManager) {
        return new StepBuilderFactory(jobRepository, dataSourceTransactionManager);
    }

    /**
     * 作业调度器
     */
    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }
    /**
     * 作业注册器
     */
    @Bean
    public MapJobRegistry mapJobRegistry() {
        return new MapJobRegistry();
    }

    /*** JobRegistryBeanPostProcessor
     *
     * @param mapJobRegistry MapJobRegistry
     * @return JobRegistryBeanPostProcessor
     */
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(MapJobRegistry mapJobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(mapJobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    /**
     * 作业线程池
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(500);
        threadPoolTaskExecutor.setKeepAliveSeconds(30000);
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        threadPoolTaskExecutor.setQueueCapacity(1024);
        threadPoolTaskExecutor.setThreadNamePrefix("Data-Job");
        return threadPoolTaskExecutor;
    }
    public static void main(String[] args) {

    }
}
