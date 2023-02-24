package com.test.repository_doors.data

import android.util.Log
import com.test.core_db.data.DoorRealm
import com.test.core_db.domain.DatabaseAccess
import com.test.core_network.data.NetworkApi
import com.test.repository_doors.domain.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DoorsRepositoryImpl(
    private val api: NetworkApi,
    private val db: DatabaseAccess
): DoorsRepository {
    override fun getDoorsFromDb(): Single<List<DoorsInfo>> {
        return Single.just(db.getDoor().map { it.toDoorsInfo() })
    }

    override fun saveDoorsFromNetwork() {
        api.getDoors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                response.data?.let {
                    db.saveDoor(it.map{map->
                        DoorRealm().apply {
                            name = map?.name
                            room  = map?.room
                            id  = map?.id
                            favorites  = map?.favorites
                            snapshot = map?.snapshot
                        }
                    })
                }
            },{
                Log.d("DOOR_INFO_ERROR",it.message.toString())
            })
    }
}