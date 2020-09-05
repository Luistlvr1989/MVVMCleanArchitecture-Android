package com.globant.cleanarchitecture.domain.entities

import java.io.Serializable
import java.util.*

data class TaskEntity(
    val id: Long? = null,
    var title: String = "",
    var description: String = "",
    var updated: Date = Date(),
    var created: Date = Date()
) : Serializable