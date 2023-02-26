package com.test.core_network.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CamerasDataResponse(
    @SerializedName("room")
    @Expose
    val room: List<String?>,

    @SerializedName("cameras")
    @Expose
    val cameras: List<CameraResponse?>
)
