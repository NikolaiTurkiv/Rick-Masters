package com.test.core_db.data

import android.util.Log
import com.test.core_db.domain.DatabaseAccess
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
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
            realm.insert(camera)
        }
        realm.commitTransaction()
        realm.close()
    }

    override fun saveDoor(doorsRealm: List<DoorRealm>) {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()

        for (doors in doorsRealm) {
            realm.insert(doors)
        }
        realm.commitTransaction()
    }

    override fun saveRoom(roomsRealm: List<RoomRealm>) {
        val realm = Realm.getInstance(config)

        realm.beginTransaction()

        for (room in roomsRealm) {
            realm.insert(room)
        }
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

}

