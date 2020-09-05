package com.globant.cleanarchitecture.domain.entities

import java.util.*

data class TaskEntity(
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    var updated: Date = Date(),
    var created: Date = Date()
)