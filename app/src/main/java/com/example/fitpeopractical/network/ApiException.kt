package com.example.fitpeopractical.network

import java.io.IOException

/**
 * Created by Jeetesh Surana.
 */
class ApiException(message: String, var errno: String?, var code: Int) : IOException(message)
