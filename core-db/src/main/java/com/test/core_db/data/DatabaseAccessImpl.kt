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

        for (camera in camerasRealm) {
            realm.insertOrUpdate(camera)
        }
        realm.commitTransaction()
    }

    override fun saveDoor(doorsRealm: List<DoorRealm>) {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
        for (doors in doorsRealm) {
            realm.insertOrUpdate(doors)
        }
        realm.commitTransaction()
    }

    override fun saveRoom(roomsRealm: List<RoomRealm>) {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()

        for (room in roomsRealm) {
            realm.insertOrUpdate(room)
        }
        realm.commitTransaction()
    }

    override fun updateDoor(doorRealm: DoorRealm) {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()

        val doorFromDb = realm.where(DoorRealm::class.java)
            .equalTo(ID, doorRealm.id)
            .findFirst()

        doorFromDb?.name = doorRealm.name
        doorFromDb?.let { realm.copyToRealm(it) }

        realm.commitTransaction()
    }

    override fun getCamera(): RealmResults<CameraRealm> {
        val realm = Realm.getInstance(config)
        return realm.where(CameraRealm::class.java).findAll()
    }

    override fun getDoor(): RealmResults<DoorRealm> {
        val realm = Realm.getInstance(config)
        return realm.where(DoorRealm::class.java).findAll()
    }

    override fun getRoom(): RealmResults<RoomRealm> {
        val realm = Realm.getInstance(config)
        return realm.where(RoomRealm::class.java).findAll()
    }

    override fun removeAll() {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

    companion object {
        private const val ID = "id"
    }

}

