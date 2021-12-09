package com.javashitang.part1.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: lilimin
 * @Date: 2019/7/6 18:48
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }),
        @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }),
        @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
public class SqlCostTimeInterceptor implements Interceptor {

    public static final Logger logger = LoggerFactory.getLogger(SqlCostTimeInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        long start = System.currentTimeMillis();
        try {
            // 执行被拦截的方法
            return invocation.proceed();
        } finally {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            long end = System.currentTimeMillis();
            long cost = end - start;
            logger.info("{}, cost is {}", sql, cost);
        }
    }
}
