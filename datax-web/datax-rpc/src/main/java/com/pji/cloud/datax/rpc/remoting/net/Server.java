package com.pji.cloud.datax.rpc.remoting.net;

import com.pji.cloud.datax.rpc.remoting.provider.XxlRpcProviderFactory;
import com.pji.cloud.datax.rpc.remoting.net.params.BaseCallback;

/**
 * server
 *
 * @author xuxueli 2015-11-24 20:59:49
 */
public interface Server {

    void setStartedCallback(BaseCallback startedCallback);

    void setStopedCallback(BaseCallback stopedCallback);

    void start(final XxlRpcProviderFactory xxlRpcProviderFactory) throws Exception;

    void stop() throws Exception;

}
