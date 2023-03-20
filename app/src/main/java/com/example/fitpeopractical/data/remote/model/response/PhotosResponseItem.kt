package com.example.fitpeopractical.data.remote.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotosResponseItem(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
): Parcelable