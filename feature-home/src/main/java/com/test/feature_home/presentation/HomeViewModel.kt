package com.test.feature_home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.repository_cameras.domain.CamerasUseCase
import com.test.repository_doors.domain.DoorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val doorsUseCase: DoorsUseCase,
    private val camerasUSeCase: CamerasUseCase,
): ViewModel() {


    fun loadDoors(){
     doorsUseCase.doors
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe({
             Log.d("DOOR_INFO",it.data.toString())
         },{

         })
    }

    fun loadCameras(){
        camerasUSeCase.cameras
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("DOOR_INFO",it.toString())
            },{

            })
    }

}
