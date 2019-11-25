package com.example.zhouyunlong.pintuan.batch.step;

import com.example.zhouyunlong.pintuan.entity.UserSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/23 18:03
 * 4
 */
@Configuration
@Slf4j
public class PushTaskStep {
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 一个简单基础的Step主要分为三个部分
     * ItemReader : 用于读取数据
     * ItemProcessor : 用于处理数据
     * ItemWriter : 用于写数据
     */
    @Bean(name = "pushStep")
    public Step handleDataStep() {
        return stepBuilderFactory.get("getData").
                // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
                <UserSubscribe, UserSubscribe>chunk(100).
                faultTolerant().retryLimit(3)
                //捕捉到异常就重试,重试3次还是异常,JOB就停止并标志失败
                .retry(Exception.class).skipLimit(3).skip(Exception.class).
                //指定ItemReader
                reader(getDataReader()).
                //指定ItemProcessor
                processor(getDataProcessor()).
                //指定ItemWriter
                writer(getDataWriter()).
                build();
    }
    @Bean
    public ItemReader<? extends UserSubscribe> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS 等方式 读入数据
        MyBatisPagingItemReader<UserSubscribe> reader=new MyBatisPagingItemReader<>();
        // JpaPagingItemReader<Access> reader = new JpaPagingItemReader<>();
        //这里选择JPA方式读数据 一个简单的 native SQL
        try {
            reader.setQueryId("com.example.zhouyunlong.pintuan.dao.UserSubscribeMapper.list");
            reader.setSqlSessionFactory(sqlSessionFactory);
            reader.setPageSize(1000);
            reader.afterPropertiesSet();
            //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
            reader.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }
    @Bean
    public ItemProcessor<UserSubscribe, UserSubscribe> getDataProcessor() {
        return userSubscribe -> {
            log.info("processor data : " + userSubscribe.toString());  //模拟  假装处理数据,这里处理就是打印一下

            return userSubscribe;
        };
    }
    @Bean
    public ItemWriter<UserSubscribe> getDataWriter() {
       return new ItemWriter<UserSubscribe>() {
           @Override
           public void write(List<? extends UserSubscribe> list) throws Exception {
               list.forEach(System.out::println);
           }
       };
    }
}
