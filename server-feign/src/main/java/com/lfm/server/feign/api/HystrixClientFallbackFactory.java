package com.lfm.server.feign.api;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<SysUserClient> {
    @Override
    public SysUserClient create(Throwable cause) {

        return Collections::emptyList;
    }
}
