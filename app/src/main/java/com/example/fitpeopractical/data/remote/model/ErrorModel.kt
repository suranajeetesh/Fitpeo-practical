package com.example.fitpeopractical.data.remote.model

data class ErrorModel(
    var message: String = "",
    var errno: String? = null,
    var code: Int = 0
)
