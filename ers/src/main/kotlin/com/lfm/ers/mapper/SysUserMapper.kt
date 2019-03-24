package com.lfm.ers.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.lfm.ers.entity.SysUser
import com.lfm.ers.entity.SysUserDetail
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository


@Repository
interface SysUserMapper :BaseMapper<SysUser>{
    /**
     * 获取分页用户列表
     */
    fun selectAll():List<SysUserDetail>
    /**
     * 删除用户
     */
    fun delById(@Param(value = "list") list: List<Int>):Int

    /**
     * 通过用户名查找用户
     */
    fun selectByUserName(name:String?):SysUser


    fun selectDetailById(userId:Int):SysUserDetail?

    fun updatePersonalDetail(user: SysUser):Int
}