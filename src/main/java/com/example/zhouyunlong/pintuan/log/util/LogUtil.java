package com.example.zhouyunlong.pintuan.log.util;

import com.example.zhouyunlong.pintuan.log.annotation.SysLog;
import org.aspectj.lang.JoinPoint;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 16:02
 * 4
 */
public class LogUtil {

    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getControllerMethodDescription(JoinPoint point) throws Exception {
        // 获取连接点目标类名
        String targetName = point.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = point.getSignature().getName();
        //获取连接点参数
        Object[] args = point.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(SysLog.class).descrption();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
