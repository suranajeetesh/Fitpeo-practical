package com.example.fitpeopractical.viewmodel

import com.example.fitpeopractical.core.uI.BaseViewModel
import com.example.fitpeopractical.data.remote.model.response.PhotosResponseItem
import com.example.fitpeopractical.network.ApiException
import com.example.fitpeopractical.repository.DemoRepository
import com.example.fitpeopractical.util.bindingAdapter.mutableLiveData
import com.example.fitpeopractical.util.extensionFunction.convertIntoErrorObjet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by JeeteshSurana.
 */

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val repository: DemoRepository
) : BaseViewModel() {

    var photoLists = mutableLiveData(ArrayList<PhotosResponseItem>())

    suspend fun getData() = withContext(Dispatchers.IO) {
        try {
            photoLists.postValue(repository.getPhotos() as ArrayList<PhotosResponseItem>?)
        } catch (e: ApiException) {
            mError.postValue(convertIntoErrorObjet(e))
        }
    }
}