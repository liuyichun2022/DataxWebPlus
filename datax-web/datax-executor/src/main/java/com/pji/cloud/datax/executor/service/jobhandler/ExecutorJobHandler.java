package com.pji.cloud.datax.executor.service.jobhandler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.pji.cloud.datatx.core.biz.model.ReturnT;
import com.pji.cloud.datatx.core.biz.model.TriggerParam;
import com.pji.cloud.datatx.core.handler.AbstractJobHandler;
import com.pji.cloud.datatx.core.handler.annotation.JobHandler;
import com.pji.cloud.datatx.core.log.JobLogger;
import com.pji.cloud.datax.executor.service.logparse.LogStatistics;
import com.pji.cloud.datax.executor.util.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alibaba.datax.core.Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pji.cloud.datax.executor.service.command.BuildCommand.buildDataXParamToMap;
import static com.pji.cloud.datax.executor.service.command.BuildCommand.builtInVar;
import static com.pji.cloud.datax.executor.service.jobhandler.DataXConstant.DEFAULT_JSON;

/**
 * DataX任务运行
 *
 * @author jingwk 2019-11-16
 */

@JobHandler(value = "executorJobHandler")
@Component
//@Slf4j
public class ExecutorJobHandler extends AbstractJobHandler {

    @Value("${datax.executor.jsonpath}")
    private String jsonPath;

    @Value("${datax.pypath}")
    private String dataXPyPath;

    @Value("${datax.executor.pythonpath}")
    private String pythonPath;



    private static final Pattern VARIABLE_PATTERN = Pattern.compile("(\\$)\\{?(\\w+)\\}?");

    @Override
    public ReturnT<String> execute(TriggerParam trigger) {

        int exitValue = -1;
        Thread errThread = null;
        String tmpFilePath;
        LogStatistics logStatistics = null;

        HashMap<String, String> keyValueMap = buildDataXParamToMap(trigger);
        String jobJson = replaceVariable(trigger.getJobJson(), keyValueMap);
        Map<String, String> buildin = builtInVar();
        jobJson = replaceVariable(jobJson, buildin);

        //Generate JSON temporary file
        tmpFilePath = generateTemJsonFile(jobJson);
        try {
            //检查运行环境
            /*checkEnv(dataXPyPath,pythonPath);
            String[] cmdarrayFinal = buildDataXExecutorCmd(trigger, tmpFilePath, dataXPyPath, pythonPath);
            String cmd = StringUtils.join(cmdarrayFinal, " ");
            JobLogger.log("------------------Command CMD is :" + cmd);
            final Process process = Runtime.getRuntime().exec(cmdarrayFinal);
            String prcsId = ProcessUtil.getProcessId(process);
            JobLogger.log("------------------DataX process id: " + prcsId);
            JOB_TEM_FILES.put(prcsId, tmpFilePath);
            //update datax process id
            HandleProcessCallbackParam prcs = new HandleProcessCallbackParam(trigger.getLogId(), trigger.getLogDateTime(), prcsId);
            ProcessCallbackThread.pushCallBack(prcs);
            // log-thread
            Thread futureThread;
            FutureTask<LogStatistics> futureTask = new FutureTask<>(() -> analysisStatisticsLog(new BufferedInputStream(process.getInputStream())));
            futureThread = new Thread(futureTask);
            futureThread.start();

            errThread = new Thread(() -> {
                try {
                    analysisStatisticsLog(new BufferedInputStream(process.getErrorStream()));
                } catch (IOException e) {
                    JobLogger.log(e);
                }
            });

            logStatistics = futureTask.get();
            errThread.start();
            // exit code: 0=success, 1=error
            exitValue = process.waitFor();
            // log-thread join
            errThread.join();*/
            System.setProperty("datax.home", "D:\\data\\code\\pujing\\datax-web\\datax-executor\\src\\main\\resources\\datax");

            String[] dataxArgs2 = {"-job", tmpFilePath, "-mode", "standalone", "-jobid", "-1"};
            try {
                Engine.entry(dataxArgs2);
            } catch (Throwable e) {
                //log.error(e.getMessage(), e);
            }
            exitValue = 0;

        } catch (Exception e) {
            JobLogger.log(e);
        } finally {
            //  删除临时文件
            if (FileUtil.exist(tmpFilePath)) {
                FileUtil.del(new File(tmpFilePath));
            }
        }
        if (exitValue == 0) {
            return new ReturnT<>(200, "logStatistics.toString()");
        } else {
            return new ReturnT<>(AbstractJobHandler.FAIL.getCode(), "command exit value(" + exitValue + ") is failed");
        }
    }

    /**
     * 替换json变量
     *
     * @param param
     * @param variableMap
     * @return {@link String}
     * @author Locki
     * @date 2020/9/18
     */
    public static String replaceVariable(final String param, Map<String, String> variableMap) {
        if (variableMap == null || variableMap.size() == 1) {
            return param;
        }
        Map<String, String> mapping = new HashMap<>();

        Matcher matcher = VARIABLE_PATTERN.matcher(param);
        while (matcher.find()) {
            String variable = matcher.group(2);
            String value = variableMap.get(variable);
            if (StringUtils.isBlank(value)) {
                value = matcher.group();
            }
            mapping.put(matcher.group(), value);
        }

        String retString = param;
        for (final String key : mapping.keySet()) {
            retString = retString.replace(key, mapping.get(key));
        }

        return retString;
    }


    private String generateTemJsonFile(String jobJson) {
        String tmpFilePath;
        String dataXHomePath = SystemUtils.getDataXHomePath();
        if (StringUtils.isNotEmpty(dataXHomePath)) {
            jsonPath = dataXHomePath + DEFAULT_JSON;
        }
        if (!FileUtil.exist(jsonPath)) {
            FileUtil.mkdir(jsonPath);
        }
        tmpFilePath = jsonPath + File.separator + "jobTmp-" + IdUtil.simpleUUID() + ".json";
        // 根据json写入到临时本地文件
        try (PrintWriter writer = new PrintWriter(tmpFilePath, "UTF-8")) {
            writer.println(jobJson);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            JobLogger.log("JSON temporary file write exception：" + e.getMessage());
        }
        return tmpFilePath;
    }

}
