package com.test.feature_home.presentation.domain

import com.test.repository_doors.domain.DoorsInfo

data class DoorsUI(
    val keyId: Int? = null,
    val name: String?,
    val room: String?,
    val id: Int?,
    val favorites: Boolean?,
    val snapshot: String?,
    var viewType: Int = 0
)

fun DoorsInfo.toDoorsUI(): DoorsUI {
    return DoorsUI(
        keyId = this.keyId,
        name = this.name,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        snapshot = this.snapshot
    )
}

fun DoorsUI.toDoorsInfo(): DoorsInfo {
    return DoorsInfo(
        keyId = this.keyId,
        name = this.name,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        snapshot = this.snapshot
    )
}
