package com.daas.datax.admin.core.route.strategy;

import com.daas.datatx.core.biz.model.ReturnT;
import com.daas.datatx.core.biz.model.TriggerParam;
import com.daas.datax.admin.core.route.AbstractExecutorRouter;

import java.util.List;

/**
 * @author  xuxueli on 17/3/10.
 */
public class ExecutorRouteFirst extends AbstractExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList){
        return new ReturnT<String>(addressList.get(0));
    }

}
