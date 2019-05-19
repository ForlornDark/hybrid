package com.lfm.server.feign.api;

import com.lfm.server.feign.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@FeignClient(value = "quick-start" ,fallbackFactory = HystrixClientFallbackFactory.class)
@RestController
interface SysUserClient {

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    List<SysUser> list();

}
