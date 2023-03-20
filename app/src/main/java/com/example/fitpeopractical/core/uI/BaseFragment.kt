package com.example.fitpeopractical.core.uI

import androidx.fragment.app.Fragment
import com.example.fitpeopractical.util.extensionFunction.hideKeyboard

/**
 * Created by JeeteshSurana.
 */

abstract class BaseFragment : Fragment() {
    override fun onPause() {
        activity?.hideKeyboard()
        super.onPause()
    }

    override fun onDestroy() {
        activity?.hideKeyboard()
        super.onDestroy()
    }
}