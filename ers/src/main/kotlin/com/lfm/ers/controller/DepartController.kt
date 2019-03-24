package com.lfm.ers.controller

import com.lfm.ers.entity.Depart
import com.lfm.ers.service.DepartService
import com.lfm.ers.vo.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
@Controller
@RequestMapping("/depart")
class DepartController {

    @Autowired
    val service:DepartService?= null

    @GetMapping("/index")
    @PreAuthorize("hasRole('admin')")
    fun index():String{
        return "depart"
    }


    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun list():List<Depart>?{
        return service!!.list();
    }

    @GetMapping(value= ["/edit"])
    @PreAuthorize("hasRole('admin')")
    fun edit():String{
      return "depart_edit"
    }

    @PostMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun delete(@RequestBody list:List<Depart>): Info<String> {

        return if(service!!.delete(list)) Info(true,"删除成功") else Info(false,"删除失败");
    }

    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun save(depart: Depart ): Info<String> {
        return if(service!!.save(depart)) Info(true,"添加成功") else Info(false,"添加失败");
    }

}