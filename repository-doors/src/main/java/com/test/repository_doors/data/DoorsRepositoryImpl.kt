package com.test.repository_doors.data

import android.util.Log
import com.test.core_db.data.DoorRealm
import com.test.core_db.domain.DatabaseAccess
import com.test.core_network.data.NetworkApi
import com.test.repository_doors.domain.DoorsInfo
import com.test.repository_doors.domain.DoorsRepository
import com.test.repository_doors.domain.toDoorsInfo
import com.test.repository_doors.domain.toDoorsRealm
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DoorsRepositoryImpl(
    private val api: NetworkApi,
    private val db: DatabaseAccess
) : DoorsRepository {

    override fun updateDoor(door: DoorsInfo) {
        Single.just(door)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    db.updateDoor(door.toDoorsRealm())
                }, {
                    Log.d(DOOR_ERROR, it.message.toString())
                }
            )
    }


    override fun getDoorsFromDb(): Single<List<DoorsInfo>> {
        return Single.just(db.getDoor().map { it.toDoorsInfo() })
    }

    override fun getDoorsNetwork(): Single<List<DoorsInfo>> {
        return api.getDoors().map { response -> response.data?.map { it!!.toDoorsInfo() } }
    }


    override fun saveDoorsFromNetwork() {
        api.getDoors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.data?.let {
                    db.saveDoor(it.map { map ->
                        DoorRealm().apply {
                            name = map?.name
                            room = map?.room
                            id = map?.id
                            favorites = map?.favorites
                            snapshot = map?.snapshot
                        }
                    })
                }
            }, {
                Log.d(DOOR_ERROR, it.message.toString())
            })
    }

    override fun saveDoors(doors: List<DoorsInfo>) {
        Single.just(doors).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    db.saveDoor(doors.map { it.toDoorsRealm() })
                }, {
                    Log.d(DOOR_ERROR, it.message.toString())
                }
            )
    }

    companion object {
        private const val DOOR_ERROR = "DOOR_ERROR"
    }
}
