package com.test.repository_doors.domain

import com.test.core_network.data.response.DoorsResponse

data class DoorsInfo(
    val name: String?,
    val room: String?,
    val id: Int?,
    val favorites: Boolean?,
    val snapshot: String?,
)

fun DoorsResponse.toDoorsInfo(): DoorsInfo{
    return DoorsInfo(
        name = this.name,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        snapshot = this.snapshot
    )
}