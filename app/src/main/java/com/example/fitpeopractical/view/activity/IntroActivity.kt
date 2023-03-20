package com.example.fitpeopractical.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.fitpeopractical.core.uI.BaseActivity

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent = Intent(
            this,
            SplashActivity::class.java
        ).apply {
            intent.extras?.let { putExtras(it) }
        }
        startActivity(intent)
        finish()
    }
}
