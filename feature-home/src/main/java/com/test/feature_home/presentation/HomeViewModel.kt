package com.test.feature_home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.feature_home.presentation.domain.DoorsUI
import com.test.feature_home.presentation.domain.toDoorsUI
import com.test.repository_cameras.domain.CameraInfo
import com.test.repository_cameras.domain.CamerasUseCase
import com.test.repository_doors.domain.DoorsInfo
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

    private val doorsList = mutableListOf<DoorsUI>()

    private val _cameraInfoLd = MutableLiveData<List<CameraInfo>>()
    val cameraInfoLD: LiveData<List<CameraInfo>> get() = _cameraInfoLd

    private val _doorsInfoLd = MutableLiveData<List<DoorsUI>>()
    val doorsInfoLD: LiveData<List<DoorsUI>> get() = _doorsInfoLd

    fun saveDoors(){
        doorsUseCase.saveDoors()
    }

    fun doorsFromDb(){
     doorsUseCase.doors
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe({
             val list = it.map { door -> door.toDoorsUI() }
             _doorsInfoLd.postValue(list)
             doorsList.addAll(list)
             Log.d("DOOR_INFO",it.toString())
         },{

         })
    }

    fun camerasFromDb(){
          camerasUseCase.cameras
            .subscribeOn(Schedulers.io())
            .subscribe({
                _cameraInfoLd.postValue(it)
                Log.d("Camera_INFO",it.toString())
            },{
                Log.d("Camera_INFO_VM",it.message.toString())
            })
    }

    fun updateDoorList(position: Int){
        doorsList[position].viewType = if(doorsList[position].viewType == 0) 1 else 0
        _doorsInfoLd.value = doorsList
    }

    fun saveCamerasToDb(){
        camerasUseCase.saveCamerasToDb()
    }

}
