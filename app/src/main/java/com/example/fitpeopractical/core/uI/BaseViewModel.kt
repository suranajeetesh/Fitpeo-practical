package com.example.fitpeopractical.core.uI

import androidx.lifecycle.ViewModel
import com.example.fitpeopractical.data.remote.model.ErrorModel
import com.example.fitpeopractical.util.bindingAdapter.mutableLiveData

/**
 * Created by JeeteshSurana.
 */

open class BaseViewModel : ViewModel() {
    var mError = mutableLiveData(ErrorModel())
}