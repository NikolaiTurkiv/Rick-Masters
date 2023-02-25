package com.test.feature_home.presentation.domain

import com.test.repository_doors.domain.DoorsInfo
import com.test.repository_doors.domain.toDoorsInfo

data class DoorsUI(
    val name: String?,
    val room: String?,
    val id: Int?,
    val favorites: Boolean?,
    val snapshot: String?,
    var viewType: Int = 0
)
fun DoorsInfo.toDoorsUI(): DoorsUI{
    return DoorsUI(
        name = this.name,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        snapshot = this.snapshot
    )
}
