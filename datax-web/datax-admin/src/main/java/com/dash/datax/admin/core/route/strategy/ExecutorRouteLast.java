package com.dash.datax.admin.core.route.strategy;

import com.dash.datax.admin.core.route.AbstractExecutorRouter;
import com.pji.cloud.datatx.core.biz.model.ReturnT;
import com.pji.cloud.datatx.core.biz.model.TriggerParam;

import java.util.List;

/**
 * @author  xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends AbstractExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
