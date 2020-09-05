package com.globant.cleanarchitecture.data.remote.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class TaskDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("updated") var updated: Date,
    @SerializedName("created") var created: Date
)