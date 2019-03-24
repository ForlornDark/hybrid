package com.lfm.ers.controller

import com.lfm.ers.entity.EquipType
import com.lfm.ers.entity.FaultInfo
import com.lfm.ers.service.EquipService
import com.lfm.ers.vo.Info
import com.lfm.ers.vo.LayPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value = ["/equip"])
class EquipController : BaseController(){

    @Autowired
    val service: EquipService?= null

    @GetMapping(value = ["/index"])
    @PreAuthorize("hasRole('admin')")
    fun index():String{
        return "equip_type"
    }

    @GetMapping(value = ["/type_edit"])
    @PreAuthorize("hasRole('admin')")
    fun typeEdit():String{
        return "equip_type_edit"
    }
    @GetMapping(value = ["/fault_edit"])
    @PreAuthorize("hasRole('admin')")
    fun faultEdit():String{
        return "equip_fault_edit"
    }

    @PostMapping(value = ["/list"])
    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    fun list(): List<EquipType> {
        return service!!.list()
    }

    @PostMapping(value = ["/delete"])
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun delete(@RequestBody list: List<EquipType>):Info<String>{
        service!!.deleteEquipType(list)
        return Info(true,"删除成功")
    }

    @ResponseBody
    @PostMapping(value= ["/save"])
    @PreAuthorize("hasRole('admin')")
    fun save(equipType: EquipType):Info<String>{
        val re = service!!.saveEquipType(equipType)
        return when (re){
            true -> Info(true,"保存成功","")
            false ->Info(false,"保存失败","")
        }
    }
    @PostMapping(value = ["/faultList"])
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun faultList(equipId:Int):LayPage<FaultInfo>{
        return service!!.getFaultList(equipId);
    }

    @PostMapping(value = ["/faultDelete"])
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun faultDelete(@RequestBody list: List<FaultInfo>):Info<String>{
        service!!.deleteFaultInfo(list)
        return Info(true,"删除成功")
    }

    @ResponseBody
    @PostMapping(value= ["/faultSave"])
    @PreAuthorize("hasRole('admin')")
    fun faultSave(faultInfo: FaultInfo):Info<String>{
        val re = service!!.saveFaultInfo(faultInfo)
        return when (re){
            true -> Info(true,"保存成功","")
            false ->Info(false,"保存失败","")
        }
    }
}