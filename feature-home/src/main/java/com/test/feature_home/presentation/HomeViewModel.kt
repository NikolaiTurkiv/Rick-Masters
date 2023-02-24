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
    private val camerasUseCase: CamerasUseCase,
): ViewModel() {


    fun saveDoors(){
        doorsUseCase.saveDoors()
    }

    fun doorsFromDb(){
     doorsUseCase.doors
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe({
             Log.d("DOOR_INFO",it.toString())
         },{

         })
    }

    fun camerasFromDb(){
          camerasUseCase.cameras
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("Camera_INFO",it.toString())
            },{
                Log.d("Camera_INFO_VM",it.message.toString())
            })
    }

    fun saveCamerasToDb(){
        camerasUseCase.saveCamerasToDb()
    }

}
