package com.example.fitpeopractical.util.bindingAdapter

import android.annotation.SuppressLint
import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.example.fitpeopractical.R
import com.example.fitpeopractical.util.extensionFunction.hide
import com.example.fitpeopractical.util.extensionFunction.show
import com.squareup.picasso.Picasso

/**
 * Created by Jeetesh Surana.
 */

fun <T> mutableLiveData(defaultValue: T? = null): MutableLiveData<T> {
    val data = MutableLiveData<T>()
    defaultValue?.let { data.value = it }
    return data
}

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

@SuppressLint("CheckResult")
fun ImageView.setPicture(
    url: String,
    placeholder: Int? = null
) {
    Picasso.get().load(url)
        .error(R.drawable.fitpeo)
        .into(this)
}


@BindingAdapter("setVisible")
fun setVisible(layout: View?, show: Boolean?) {
    if (layout != null && show != null && show) {
        layout.show()
    } else {
        layout?.hide()
    }
}

@BindingAdapter("setImageUrl")
fun setImageUrl(image: AppCompatImageView, url: String?) {
    if (!url.isNullOrBlank()) {
        image.setPicture(url)
    }
}