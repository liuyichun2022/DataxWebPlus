package com.daas.datax.rpc.remoting.invoker.annotation;

import com.daas.datax.rpc.remoting.net.AbstractClient;
import com.daas.datax.rpc.serialize.AbstractSerializer;
import com.daas.datax.rpc.serialize.impl.HessianSerializer;
import com.daas.datax.rpc.remoting.invoker.call.CallType;
import com.daas.datax.rpc.remoting.invoker.route.LoadBalance;
import com.daas.datax.rpc.remoting.net.impl.netty.client.NettyClient;

import java.lang.annotation.*;

/**
 * rpc service annotation, skeleton of stub ("@Inherited" allow service use "Transactional")
 *
 * @author 2015-10-29 19:44:33
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlRpcReference {

    Class<? extends AbstractClient> client() default NettyClient.class;

    Class<? extends AbstractSerializer> serializer() default HessianSerializer.class;

    CallType callType() default CallType.SYNC;

    LoadBalance loadBalance() default LoadBalance.ROUND;

    //Class<?> iface;
    String version() default "";

    long timeout() default 1000;

    String address() default "";

    String accessToken() default "";

    //XxlRpcInvokeCallback invokeCallback() ;

}
