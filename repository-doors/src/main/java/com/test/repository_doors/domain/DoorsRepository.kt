package com.test.repository_doors.domain

import io.reactivex.rxjava3.core.Single

interface DoorsRepository {
    fun updateDoor(door: DoorsInfo)
    fun getDoorsFromDb(): Single<List<DoorsInfo>>
    fun getDoorsNetwork(): Single<List<DoorsInfo>>
    fun saveDoorsFromNetwork()
    fun saveDoors(doors: List<DoorsInfo>)
}