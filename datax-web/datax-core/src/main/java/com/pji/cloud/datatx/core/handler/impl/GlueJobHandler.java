package com.pji.cloud.datatx.core.handler.impl;

import com.pji.cloud.datatx.core.biz.model.ReturnT;
import com.pji.cloud.datatx.core.biz.model.TriggerParam;
import com.pji.cloud.datatx.core.handler.AbstractJobHandler;
import com.pji.cloud.datatx.core.log.JobLogger;

/**
 * glue job handler
 * @author xuxueli 2016-5-19 21:05:45
 */
public class GlueJobHandler extends AbstractJobHandler {

	private long glueUpdatetime;
	private AbstractJobHandler jobHandler;
	public GlueJobHandler(AbstractJobHandler jobHandler, long glueUpdatetime) {
		this.jobHandler = jobHandler;
		this.glueUpdatetime = glueUpdatetime;
	}
	public long getGlueUpdatetime() {
		return glueUpdatetime;
	}

	@Override
	public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
		JobLogger.log("----------- glue.version:"+ glueUpdatetime +" -----------");
		return jobHandler.execute(tgParam);
	}
}
