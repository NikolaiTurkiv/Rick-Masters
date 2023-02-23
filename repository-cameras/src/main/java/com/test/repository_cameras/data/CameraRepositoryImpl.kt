package com.test.repository_cameras.data

import android.util.Log
import com.test.core_db.data.CameraRealm
import com.test.core_db.domain.DatabaseAccess
import com.test.core_network.data.NetworkApi
import com.test.repository_cameras.domain.CameraInfo
import com.test.repository_cameras.domain.CamerasRepository
import com.test.repository_cameras.domain.FullCamerasInfo
import com.test.repository_cameras.domain.toCameraInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CameraRepositoryImpl(
    private val api: NetworkApi,
    private val db: DatabaseAccess
) : CamerasRepository {

    override fun saveCamerasFromNetwork() {

        api.getCameras()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                db.saveCamera(response.data.cameras.map { map->

                    Log.d("Camera_INFO_add",map.toString())
                    CameraRealm().apply {
                        name = map?.name
                        snapshot = map?.snapshot
                        room = map?.room
                        id = map?.id
                        favorites = map?.favorites
                        rec = map?.rec
                    }
                })

             },{
                Log.d("Camera_INFO_message",it.message.toString())
            })
    }

    override fun getCamerasFromBd(): Single<List<CameraInfo>> {





        return Single.just(db.getCamera().map { it.toCameraInfo() })
    }
}