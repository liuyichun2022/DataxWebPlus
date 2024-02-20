package com.dash.datax.admin.core.route.strategy;

import com.dash.datax.admin.core.route.AbstractExecutorRouter;
import com.pji.cloud.datatx.core.biz.model.ReturnT;
import com.pji.cloud.datatx.core.biz.model.TriggerParam;

import java.util.List;
import java.util.Random;

/**
 * @author  xuxueli on 17/3/10.
 */
public class ExecutorRouteRandom extends AbstractExecutorRouter {

    private static Random localRandom = new Random();

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        String address = addressList.get(localRandom.nextInt(addressList.size()));
        return new ReturnT<>(address);
    }

}
