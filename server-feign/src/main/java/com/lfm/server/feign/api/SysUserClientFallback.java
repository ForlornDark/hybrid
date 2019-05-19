package com.lfm.server.feign.api;

import com.lfm.server.feign.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
//@Component
public class SysUserClientFallback implements SysUserClient {
    @Override
    public List<SysUser> list() {
        return Collections.emptyList();
    }
}
