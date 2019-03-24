package com.lfm.ers.service

import com.lfm.ers.entity.Depart

interface DepartService {
    /**
     * 保存
     */
    fun save(depart: Depart):Boolean

    fun delete(list: List<Depart>):Boolean

    fun list():List<Depart>
}