package com.test.core_network.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FullCameraInfoResponse(
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("data")
    @Expose
    val data: CamerasDataResponse,
)