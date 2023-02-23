package com.test.core_db.domain

import com.test.core_db.data.CameraRealm
import com.test.core_db.data.DoorRealm
import com.test.core_db.data.RoomRealm
import io.realm.RealmResults

interface DatabaseAccess {
    fun saveCamera(cameraRealm: List<CameraRealm>)
    fun saveDoor(doorRealm: List<DoorRealm>)
    fun saveRoom(roomsRealm: List<RoomRealm>)
    fun getCamera(): RealmResults<CameraRealm>
    fun getDoor(): RealmResults<DoorRealm>
    fun getRoom(): RealmResults<RoomRealm>
    fun removeAll()
}