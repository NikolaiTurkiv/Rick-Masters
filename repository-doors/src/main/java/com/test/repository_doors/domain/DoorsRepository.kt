package com.test.repository_doors.domain

import io.reactivex.rxjava3.core.Single

interface DoorsRepository {
    fun getDoorsFromDb(): Single<List<DoorsInfo>>
    fun saveDoorsFromNetwork()
}