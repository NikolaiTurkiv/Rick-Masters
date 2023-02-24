package com.test.repository_cameras.domain

import com.test.core_db.data.CameraRealm
import com.test.core_network.data.response.CameraResponse

data class CameraInfo(
    val name: String?,
    val snapshot: String?,
    val room: String?,
    val id: Int?,
    val favorites: Boolean?,
    val rec: Boolean?,

)

fun CameraResponse.toCameraInfo() : CameraInfo{
    return CameraInfo(
        name = this.name,
        snapshot = this.snapshot,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        rec = this.rec
    )
}

fun CameraRealm.toCameraInfo(): CameraInfo{
    return CameraInfo(
        name = this.name,
        snapshot = this.snapshot,
        room = this.room,
        id = this.id,
        favorites = this.favorites,
        rec = this.rec
    )
}