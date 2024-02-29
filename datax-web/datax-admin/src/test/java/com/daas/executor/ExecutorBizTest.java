/*
package com.daas.executor;

import com.daas.datatx.core.biz.ExecutorBiz;
import com.daas.datatx.core.biz.model.ReturnT;
import com.daas.datatx.core.biz.model.TriggerParam;
import com.daas.datatx.core.enums.ExecutorBlockStrategyEnum;
import com.daas.datatx.core.glue.GlueTypeEnum;
import com.daas.datax.rpc.remoting.invoker.XxlRpcInvokerFactory;
import com.daas.datax.rpc.remoting.invoker.call.CallType;
import com.daas.datax.rpc.remoting.invoker.reference.XxlRpcReferenceBean;
import com.daas.datax.rpc.remoting.invoker.route.LoadBalance;
import com.daas.datax.rpc.remoting.net.impl.netty.http.client.NettyHttpClient;
import com.daas.datax.rpc.serialize.impl.HessianSerializer;

*/
/**
 * executor-api client, test
 *
 * @author  xuxueli on 17/5/12.
 *//*

public class ExecutorBizTest {

    public static void main(String[] args) throws Exception {

        // param
        String jobHandler = "demoJobHandler";
        String params = "";

        runTest(jobHandler, params);
    }

    */
/**
     * run jobhandler
     *
     * @param jobHandler
     * @param params
     *//*

    private static void runTest(String jobHandler, String params) throws Exception {
        // trigger data
        TriggerParam triggerParam = new TriggerParam();
        triggerParam.setJobId(1);
        triggerParam.setExecutorHandler(jobHandler);
        triggerParam.setExecutorParams(params);
        triggerParam.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.COVER_EARLY.name());
        triggerParam.setGlueType(GlueTypeEnum.DATAX.name());
        triggerParam.setGlueSource(null);
        triggerParam.setGlueUpdatetime(System.currentTimeMillis());
        triggerParam.setLogId(1);
        triggerParam.setLogDateTime(System.currentTimeMillis());

        // do remote trigger
        String accessToken = null;

        XxlRpcReferenceBean referenceBean = new XxlRpcReferenceBean();
        referenceBean.setClient(NettyHttpClient.class);
        referenceBean.setSerializer(HessianSerializer.class);
        referenceBean.setCallType(CallType.SYNC);
        referenceBean.setLoadBalance(LoadBalance.ROUND);
        referenceBean.setIface(ExecutorBiz.class);
        referenceBean.setVersion(null);
        referenceBean.setTimeout(3000);
        referenceBean.setAddress("127.0.0.1:9999");
        referenceBean.setAccessToken(accessToken);
        referenceBean.setInvokeCallback(null);
        referenceBean.setInvokerFactory(null);

        ExecutorBiz executorBiz = (ExecutorBiz) referenceBean.getObject();

        ReturnT<String> runResult = executorBiz.run(triggerParam);

        System.out.println(runResult);
        XxlRpcInvokerFactory.getInstance().stop();
    }

}
*/
