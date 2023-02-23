package com.test.repository_doors.data

import android.util.Log
import com.test.core_network.data.NetworkApi
import com.test.repository_doors.domain.DoorsRepository
import com.test.repository_doors.domain.FullDoorsInfo
import com.test.repository_doors.domain.toFullDoorsInfo
import io.reactivex.rxjava3.core.Single

class DoorsRepositoryImpl(
    private val api: NetworkApi
): DoorsRepository {
    override fun getDoors(): Single<FullDoorsInfo> {
        return api.getDoors().map {response->
            response.toFullDoorsInfo()
        }
    }
}