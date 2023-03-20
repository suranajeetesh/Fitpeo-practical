package com.example.fitpeopractical.core.uI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitpeopractical.util.extensionFunction.hideKeyboard
import com.example.fitpeopractical.util.extensionFunction.setTheme

/**
 * Created by JeeteshSurana.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setTheme()
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window?.setTheme()
        }
    }
}