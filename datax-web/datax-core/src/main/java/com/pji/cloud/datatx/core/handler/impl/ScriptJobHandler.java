package com.pji.cloud.datatx.core.handler.impl;

import com.pji.cloud.datatx.core.biz.model.ReturnT;
import com.pji.cloud.datatx.core.biz.model.TriggerParam;
import com.pji.cloud.datatx.core.log.JobFileAppender;
import com.pji.cloud.datatx.core.log.JobLogger;
import com.pji.cloud.datatx.core.glue.GlueTypeEnum;
import com.pji.cloud.datatx.core.handler.AbstractJobHandler;
import com.pji.cloud.datatx.core.util.ScriptUtil;
import com.pji.cloud.datatx.core.util.ShardingUtil;

import java.io.File;

/**
 * @author  xuxueli on 17/4/27.
 */
public class ScriptJobHandler extends AbstractJobHandler {

    private int jobId;
    private long glueUpdatetime;
    private String gluesource;
    private GlueTypeEnum glueType;

    public ScriptJobHandler(int jobId, long glueUpdatetime, String gluesource, GlueTypeEnum glueType){
        this.jobId = jobId;
        this.glueUpdatetime = glueUpdatetime;
        this.gluesource = gluesource;
        this.glueType = glueType;

        // clean old script file
        File glueSrcPath = new File(JobFileAppender.getGlueSrcPath());
        if (glueSrcPath.exists()) {
            File[] glueSrcFileList = glueSrcPath.listFiles();
            if (glueSrcFileList!=null && glueSrcFileList.length>0) {
                for (File glueSrcFileItem : glueSrcFileList) {
                    if (glueSrcFileItem.getName().startsWith(jobId +"_")) {
                        glueSrcFileItem.delete();
                    }
                }
            }
        }

    }

    public long getGlueUpdatetime() {
        return glueUpdatetime;
    }


    @Override
    public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
        if (!glueType.isScript()) {
            return new ReturnT<>(AbstractJobHandler.FAIL.getCode(), "glueType[" + glueType + "] invalid.");
        }

        // cmd
        String cmd = glueType.getCmd();

        // make script file
        String scriptFileName = JobFileAppender.getGlueSrcPath()
                .concat(File.separator)
                .concat(String.valueOf(jobId))
                .concat("_")
                .concat(String.valueOf(glueUpdatetime))
                .concat(glueType.getSuffix());
        File scriptFile = new File(scriptFileName);
        if (!scriptFile.exists()) {
            ScriptUtil.markScriptFile(scriptFileName, gluesource);
        }

        // log file
        String logFileName = JobFileAppender.CONTEXT_HOLDER.get();

        // script params：0=param、1=分片序号、2=分片总数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        String[] scriptParams = new String[3];
        scriptParams[0] = tgParam.getExecutorParams();
        scriptParams[1] = String.valueOf(shardingVO.getIndex());
        scriptParams[2] = String.valueOf(shardingVO.getTotal());

        // invoke
        JobLogger.log("----------- script file:"+ scriptFileName +" -----------");
        int exitValue = ScriptUtil.execToFile(cmd, scriptFileName, logFileName,tgParam.getLogId(),tgParam.getLogDateTime(), scriptParams);

        if (exitValue == 0) {
            return AbstractJobHandler.SUCCESS;
        } else {
            return new ReturnT<>(AbstractJobHandler.FAIL.getCode(), "script exit value(" + exitValue + ") is failed");
        }
    }

}