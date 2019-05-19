package com.lfm.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SysUser {
    private String userName;

    private String password;
    private String port;
}
