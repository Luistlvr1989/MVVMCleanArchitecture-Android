package com.globant.cleanarchitecture.data.mapper

import com.globant.cleanarchitecture.data.local.models.Task
import com.globant.cleanarchitecture.data.remote.models.TaskDto
import com.globant.cleanarchitecture.domain.entities.TaskEntity

fun TaskEntity.toModel() = Task(
    id = id,
    title = title,
    description = description,
    updated = updated,
    created = created
)

fun Task.toEntity() = TaskEntity(
    id = id!!,
    title = title,
    description = description,
    updated = updated,
    created = created
)

fun TaskDto.toEntity() = TaskEntity(
    id = id,
    title = title,
    description = description,
    updated = updated,
    created = created
)