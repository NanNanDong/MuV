package com.newx.muv.support.factory;

import com.newx.muv.entity.bo.AccountLog;
import com.newx.muv.entity.bo.OperationLog;

import java.util.Date;

/* *
 * @Author newx
 * @Description 日志对象工厂
 * @Date 9:50 2018/4/22
 */
public class LogFactory {

    private LogFactory() {

    }

    public static AccountLog createAccountLog(String userId, String logName, String ip, Short succeed, String message) {
        AccountLog accountLog = new AccountLog();
        accountLog.setUserId(userId);
        accountLog.setLogName(logName);
        accountLog.setIp(ip);
        accountLog.setSucceed(succeed);
        accountLog.setMessage(message);
        accountLog.setCreateTime(new Date());
        return accountLog;
    }

    public static OperationLog createOperationLog(String userId, String logName, String api, String method, Short succeed, String message) {
        OperationLog operationLog = new OperationLog();
        operationLog.setUserId(userId);
        operationLog.setLogName(logName);
        operationLog.setApi(api);
        operationLog.setMethod(method);
        operationLog.setSucceed(succeed);
        operationLog.setMessage(message);
        operationLog.setCreateTime(new Date());
        return operationLog;
    }
}
