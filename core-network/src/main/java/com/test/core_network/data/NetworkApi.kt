package com.test.core_network.data

import com.test.core_network.data.response.FullCameraInfoResponse
import com.test.core_network.data.response.FullDoorsInfoResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface NetworkApi {
    companion object{
        private const val CAMERA_ENDPOINT = "cameras"
        private const val DOORS_ENDPOINT = "doors"
    }

    @Streaming
    @GET(CAMERA_ENDPOINT)
    fun getCameras(): Single<FullCameraInfoResponse>


    @Streaming
    @GET(DOORS_ENDPOINT)
    fun getDoors(): Single<FullDoorsInfoResponse>

}