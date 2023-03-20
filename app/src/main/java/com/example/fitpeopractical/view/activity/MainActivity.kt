package com.example.fitpeopractical.view.activity

import android.os.Bundle
import com.example.fitpeopractical.R
import com.example.fitpeopractical.core.uI.BaseActivity
import com.example.fitpeopractical.databinding.ActivityMainBinding
import com.example.fitpeopractical.util.extensionFunction.addReplaceFragment
import com.example.fitpeopractical.util.extensionFunction.backPressManagement
import com.example.fitpeopractical.view.fragment.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addReplaceFragment(
            R.id.fl_container,
            DashboardFragment(),
            addFragment = false,
            addToBackStack = false
        )
    }

    override fun onBackPressed() {
        backPressManagement()
    }
}