package com.daas.datax.executor.core.config;

import com.daas.datatx.core.executor.impl.JobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class DataXConfig {
    private Logger logger = LoggerFactory.getLogger(DataXConfig.class);

    @Value("${datax.job.admin.addresses}")
    private String adminAddresses;

    @Value("${datax.job.executor.appname}")
    private String appName;

    @Value("${datax.job.executor.ip}")
    private String ip;

    @Value("${datax.job.executor.port}")
    private int port;

    @Value("${datax.job.accessToken}")
    private String accessToken;

    @Value("${datax.job.executor.logpath}")
    private String logPath;

    @Value("${datax.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean
    public JobSpringExecutor JobExecutor() {
        logger.info(">>>>>>>>>>> datax-web config init.");
        JobSpringExecutor jobSpringExecutor = new JobSpringExecutor();
        jobSpringExecutor.setAdminAddresses(adminAddresses);
        jobSpringExecutor.setAppName(appName);
        jobSpringExecutor.setIp(ip);
        jobSpringExecutor.setPort(port);
        jobSpringExecutor.setAccessToken(accessToken);
        jobSpringExecutor.setLogPath(logPath);
        jobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return jobSpringExecutor;
    }
}