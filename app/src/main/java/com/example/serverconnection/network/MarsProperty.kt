package com.example.serverconnection.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MarsProperty(
    val id: String,
    @Json(name = "img_src") val imageSrcUrl: String,
    val type: String,
    val price: Double
): Parcelable
