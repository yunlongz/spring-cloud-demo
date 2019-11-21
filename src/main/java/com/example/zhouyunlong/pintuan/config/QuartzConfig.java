package com.example.zhouyunlong.pintuan.config;

import com.example.zhouyunlong.pintuan.task.WxPushTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/20 23:25
 * 4
 */
@Configuration
public class QuartzConfig {
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(WxPushTask task) {
        // WxPushTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会并发执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(true);
        // 设置任务的名字
        jobDetail.setName("scheduler");
        // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        jobDetail.setGroup("scheduler_group");

        /*
         * 这两行代码表示执行task对象中的scheduleTest方法。定时执行的逻辑都在scheduleTest。
         */
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("wxPushTask");
        return jobDetail;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail.getObject());
        // 表示每隔6秒钟执行一次
        cronTriggerFactoryBean.setCronExpression("0/6 * * * * ?");
        // trigger的name
        cronTriggerFactoryBean.setName("myTrigger");
        return cronTriggerFactoryBean;

    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动5秒后  ，定时器才开始启动
        bean.setStartupDelay(5);
        // 注册定时触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }
    //多任务时的Scheduler，动态设置Trigger。一个SchedulerFactoryBean可能会有多个Trigger
    @Bean(name = "multitaskScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(){
        return new SchedulerFactoryBean();
    }
}
