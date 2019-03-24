//package com.lfm.ers.security
//
//import org.springframework.security.access.ConfigAttribute
//import java.util.ArrayList
//import org.springframework.security.web.util.matcher.RequestMatcher
//import java.util.HashSet
//import org.springframework.security.web.FilterInvocation
//import org.springframework.security.access.SecurityConfig
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher
//import java.util.HashMap
//import org.springframework.beans.factory.annotation.Autowired
//import org.slf4j.LoggerFactory
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
//
//
///**
// * 服务器启动时，会将数据库中所有权限和资源提取出来，放在一个map里，等用户登录到该系统时，就会使用到map，从而判断该用户是否有这个权限。
// * @author Boomer
// * @date 2017/10/16 10:47
// */
//class CustomInvocationSecurityMetadataSource @Autowired
//constructor(private val securityService: SecurityService) : FilterInvocationSecurityMetadataSource {
//
//    /*** 所有的资源和权限的映射就存在这里  */
//    private val requestMap = HashMap<RequestMatcher, Collection<ConfigAttribute>>()
//
//    private val allAttribute = HashSet<ConfigAttribute>()
//
//    init {
//        initResources()
//    }
//
//    /**
//     * 初始化所有的资源,这个会在容器运行的时候的构造方法里调用，spring Security只会限制当前与角色关联的资源权限
//     */
//    fun initResources() {
//        LOGGER.debug("init SecurityMetadataSource load all resources")
//        // 读取所有的资源,和资源相关联的的权限
//        // 读取所有权限点
//        val allRoleEntity = securityService.getAllRoleEntity()
//
//        for (role in allRoleEntity) {
//            val roleString = role.getRole()
//            LOGGER.debug("add role named:[{}]", roleString)
//            val attrConfig = SecurityConfig(roleString)
//            allAttribute.add(attrConfig)
//        }
//        // 读取所有资源
//        val allResources = securityService.findAllResources()
//        // 循环所有资源
//        for (resourceEntiry in allResources) {
//            // 按照资源查询和资源相关的权限点
//            val authEntities = securityService.getRoleByResource(resourceEntiry.getUuid())
//            // 把此关系保存到requestMap里
//            // 获取资源
//            val resourceContent = resourceEntiry.getResUrl()
//            // 把url资源转化为一个spring的工具类,请求匹配器类
//            LOGGER.debug("add new requestmatcher with [{}]", resourceContent)
//
//            val matcher = AntPathRequestMatcher(resourceContent)
//            // 循环权限 定义一个权限的集合,和此资源对应起来,添加到HashMap里
//            val array = ArrayList<ConfigAttribute>(authEntities.size)
//            for (roleEntity in authEntities) {
//                // 转化权限对象为SecurityConfig
//                val securityConfig = SecurityConfig(roleEntity.getRole())
//                array.add(securityConfig)
//            }
//            requestMap[matcher] = array
//        }
//    }
//
//    /**
//     * 根据资源获取需要的权限名称
//     */
//    @Throws(IllegalArgumentException::class)
//    override fun getAttributes(`object`: Any): Collection<ConfigAttribute> {
//        // 把对象转化为请求
//        val request = (`object` as FilterInvocation)
//                .request
//        // 循环整个Map 看看有没有可以匹配的,如果有匹配的就立刻返回
//        val attrHashMap = HashSet<ConfigAttribute>()
//        for ((key, value) in requestMap) {
//            if (key.matches(request)) {
//                attrHashMap.addAll(value)
//            }
//        }
//        return if (attrHashMap.size > 0) {
//            // 如果有匹配的就转成ArrayList,然后返回list
//            ArrayList(
//                    attrHashMap)
//        } else Collections.emptyList()
//    }
//
//    /**
//     * 获取所有权限点
//     */
//    override fun getAllConfigAttributes(): Collection<ConfigAttribute> {
//        return this.allAttribute
//    }
//
//    override fun supports(clazz: Class<*>): Boolean {
//        // TODO Auto-generated method stub
//        return true
//    }
//
//}