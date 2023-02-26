package com.test.core_network.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DoorsResponse(
    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("room")
    @Expose
    val room: String?,

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("favorites")
    @Expose
    val favorites: Boolean?,

    @SerializedName("snapshot")
    @Expose
    val snapshot: String?,
)
