package com.test.core_db.data

import com.test.core_db.domain.DatabaseAccess
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import javax.inject.Inject

class DatabaseAccessImpl @Inject constructor(
    private val config: RealmConfiguration
) : DatabaseAccess {

    override fun saveCamera(camerasRealm: List<CameraRealm>) {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
        for (camera in camerasRealm){
            realm.insert(camera)
        }


    }

    override fun saveDoor(doorRealm: List<DoorRealm>) {
        TODO("Not yet implemented")
    }

    override fun saveRoom(roomRealm: RoomRealm) {
        TODO("Not yet implemented")
    }

    override fun getCamera(): List<CameraRealm> {
        TODO("Not yet implemented")
    }

    override fun getDoor(): List<DoorRealm> {
        TODO("Not yet implemented")
    }

    override fun getRoom(): RealmResults<RoomRealm> {
        TODO("Not yet implemented")
    }

    override fun removeAll() {
        TODO("Not yet implemented")
    }

}