package com.lfm.ers.config

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonCreator



class SampleMessage {
    private var id: Int? = null

    private var message: String = ""

    @JsonCreator
    constructor(@JsonProperty("id") id: Int?,
                      @JsonProperty("message") message: String){
        this.id = id
        this.message = message
    }

    fun getId(): Int? {
        return this.id
    }

    fun getMessage(): String {
        return this.message
    }

    override fun toString(): String {
        return "SampleMessage{id=" + this.id + ", message='" + this.message + "'}"
    }
}