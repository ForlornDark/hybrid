package com.lfm.ers.service.impl

import com.lfm.ers.entity.Depart
import com.lfm.ers.mapper.DepartMapper
import com.lfm.ers.service.DepartService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
open class DepartServiceImpl:DepartService {

    @Autowired
    private val mapper:DepartMapper?=null

    @Transactional
    override fun save(depart: Depart): Boolean {
        var re = false
        if (depart.id == null){
            depart.createTime = LocalDateTime.now()
            mapper!!.insertSelective(depart)
            val de = Depart()
            de.id = depart.id
            if(depart.parentId == null){
                de.code = "" + de.id
            }else{
                de.code = depart.code + "-"+de.id
            }
            re = mapper.updateByPrimaryKeySelective(de) > 0
        }else{
            depart.updateTime = LocalDateTime.now()
            re  = mapper!!.updateByPrimaryKeySelective(depart) > 0
        }
        return re
    }


    override fun delete(list: List<Depart>): Boolean {
        var re = false
        for (de in list) {
            re = mapper!!.deleteByPrimaryKey(de.id) > 0
            if (!re)
                break
        }
        return re
    }

    override fun list(): List<Depart> {
        return mapper!!.selectAll()
    }
}