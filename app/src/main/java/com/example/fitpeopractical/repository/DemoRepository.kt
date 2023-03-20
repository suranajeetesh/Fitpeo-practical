package com.example.fitpeopractical.repository

import android.content.Context
import com.example.fitpeopractical.data.remote.model.response.PhotosResponseItem
import com.example.fitpeopractical.network.ApiRestService
import com.example.fitpeopractical.network.SafeApiRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DemoRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val api: ApiRestService
) : SafeApiRequest(context) {

    suspend fun getPhotos(): List<PhotosResponseItem> {
        return apiRequest { api.getPhotos() }
    }
}