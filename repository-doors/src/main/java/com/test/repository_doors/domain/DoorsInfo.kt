package com.test.repository_doors.domain

import com.test.core_db.data.DoorRealm
import com.test.core_network.data.response.DoorsResponse

data class DoorsInfo(
    val keyId: Int?,
    val name: String?,
    val room: String?,
    val id: Int?,
    val favorites: Boolean?,
    val snapshot: String?,
)

fun DoorsResponse.toDoorsInfo(): DoorsInfo {
    return DoorsInfo(
        keyId = null,
        name = this.name,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        snapshot = this.snapshot
    )
}

fun DoorRealm.toDoorsInfo(): DoorsInfo {
    return DoorsInfo(
        keyId = this.keyId,
        name = this.name,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        snapshot = this.snapshot
    )
}

fun DoorsInfo.toDoorsRealm(): DoorRealm {
    val door = this
    return DoorRealm().apply {
        keyId = door.keyId
        name = door.name
        room = door.room
        id = door.id
        favorites = door.favorites
        snapshot = door.snapshot
    }
}