package com.example.zhouyunlong.pintuan.entity;
import	java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/7 22:48
 * 4
 */
@Data
@Builder
public class MsmpUser {
    private Long id;
    private int age;
    @NonNull
    private String name;
    private String address;
}
