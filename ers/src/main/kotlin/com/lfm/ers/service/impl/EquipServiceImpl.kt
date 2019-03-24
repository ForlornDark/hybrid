package com.lfm.ers.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.lfm.ers.entity.EquipType
import com.lfm.ers.entity.FaultInfo
import com.lfm.ers.mapper.EquipTypeMapper
import com.lfm.ers.mapper.FaultInfoMapper
import com.lfm.ers.service.EquipService
import com.lfm.ers.vo.LayPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
open class EquipServiceImpl:EquipService {

    @Autowired
    val mapper:EquipTypeMapper?=null

    @Autowired
    val faultInfoMapper:FaultInfoMapper?=null

    override fun list(): List<EquipType> {
        return mapper!!.selectList()
    }

    override fun getFaultList(equipId: Int): LayPage<FaultInfo> {
        PageHelper.startPage<FaultInfo>(0,0)
        val list = faultInfoMapper!!.selectByEquipId(equipId)
        val page = PageInfo<FaultInfo>(list)
        System.out.println("ddddddddddddddd:"+list[0].createTime)
        return LayPage("0","",page.total.toInt(),page.list)
    }

    override fun saveEquipType(equipType: EquipType): Boolean {
        return if(equipType.id == null){
            equipType.createTime = LocalDateTime.now()
            mapper!!.insertSelective(equipType) > 0
        }else{
            equipType.updateTime = LocalDateTime.now()
            mapper!!.updateByPrimaryKeySelective(equipType) > 0
        }
    }

    override fun saveFaultInfo(faultInfo: FaultInfo): Boolean {
        var re:Boolean
        if(faultInfo.id == null){
            faultInfo.createTime = LocalDateTime.now()
            re = faultInfoMapper!!.insertSelective(faultInfo) > 0
        }else{
            faultInfo.updateTime = LocalDateTime.now()
            re = faultInfoMapper!!.updateByPrimaryKeySelective(faultInfo) > 0
        }
        return  re;
    }

    @Transactional
    override fun deleteEquipType(list: List<EquipType>) {
        for (i in list){
            mapper!!.deleteByPrimaryKey(i.id)
        }
    }

    override fun deleteFaultInfo(list: List<FaultInfo>) {
        for (i in list){
            faultInfoMapper!!.deleteByPrimaryKey(i.id)
        }
    }
}