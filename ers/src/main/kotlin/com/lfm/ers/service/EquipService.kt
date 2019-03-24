package com.lfm.ers.service

import com.lfm.ers.entity.EquipType
import com.lfm.ers.entity.FaultInfo
import com.lfm.ers.vo.LayPage

interface EquipService {

    fun list():List<EquipType>

    fun getFaultList(equipId:Int):LayPage<FaultInfo>

    fun saveEquipType(equipType: EquipType):Boolean

    fun saveFaultInfo(faultInfo: FaultInfo):Boolean

    fun deleteEquipType(list: List<EquipType>)

    fun deleteFaultInfo(list: List<FaultInfo>)
}