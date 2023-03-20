package com.example.fitpeopractical.util.extensionFunction

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.fitpeopractical.data.remote.model.ErrorModel
import com.example.fitpeopractical.network.ApiException

/**
 * Created by Jeetesh surana.
 */

/**
 * Add replace fragment
 *
 * @param container
 * @param fragment
 * @param addFragment
 * @param addToBackStack
 */
fun FragmentActivity.addReplaceFragment(
    @IdRes container: Int,
    fragment: Fragment,
    addFragment: Boolean,
    addToBackStack: Boolean
) {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    if (addFragment) {
        transaction.add(
            container,
            fragment,
            fragment.javaClass.simpleName
        )
    } else {
        transaction.replace(
            container,
            fragment,
            fragment.javaClass.simpleName
        )
    }
    if (addToBackStack) {
        transaction.addToBackStack(fragment.tag)
    }
    hideKeyboard()
    if (!supportFragmentManager.isDestroyed) {
        transaction.commit()
    }
}

/**
 * Add replace fragment
 *
 * @param container
 * @param fragment
 * @param addFragment
 * @param addToBackStack
 */
fun FragmentActivity.addReplaceFragmentWithSharedElement(
    @IdRes container: Int,
    fragment: Fragment,
    addFragment: Boolean,
    addToBackStack: Boolean,
    sharedView: View? = null,
    sharedName: String? = null
) {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    if (addFragment) {
        transaction.add(
            container,
            fragment,
            fragment.javaClass.simpleName
        )
    } else {
        transaction.replace(
            container,
            fragment,
            fragment.javaClass.simpleName
        )
    }
    if (addToBackStack) {
        transaction.addToBackStack(fragment.tag)
    }

    if (sharedView != null && !sharedName.isNullOrEmpty()) {
        transaction.setReorderingAllowed(true)
        transaction.addSharedElement(sharedView, sharedName)
    }
    hideKeyboard()
    if (!supportFragmentManager.isDestroyed) {
        transaction.commit()
    }
}

/**
 * Add replace fragment
 *
 * @param container
 * @param fragment
 * @param addFragment
 * @param addToBackStack
 */
fun FragmentActivity.addReplaceFragmentWithAnimation(
    @IdRes container: Int,
    fragment: Fragment,
    addFragment: Boolean,
    addToBackStack: Boolean,
    enterAnimation: Int,
    exitAnimation: Int
) {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    transaction.setCustomAnimations(
        enterAnimation,
        exitAnimation
    )
    if (addFragment) {
        transaction.add(
            container,
            fragment,
            fragment.javaClass.simpleName
        )
    } else {
        transaction.replace(
            container,
            fragment,
            fragment.javaClass.simpleName
        )
    }
    if (addToBackStack) {
        transaction.addToBackStack(fragment.tag)
    }
    hideKeyboard()
    if (!supportFragmentManager.isDestroyed) {
        transaction.commit()
    }
}


//hide the keyboard
fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) view = View(this)
    imm.hideSoftInputFromWindow(
        view.windowToken,
        0
    )
}



fun Fragment.gotoBack() {
    activity?.supportFragmentManager?.popBackStack()
}

const val fullScreenFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_FULLSCREEN
const val hideNavigationFlag =
    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
const val hideStatusBar = View.STATUS_BAR_HIDDEN
const val drawStatusBarFlag = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
const val immersiveStickyFlag = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
const val noLimitFlag = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
const val keepScreenOn =
    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
const val lightStatusFlag = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

enum class Theme { FULL_SCREEN, GLOBAL }

@SuppressLint("ObsoleteSdkInt")
fun Window.setTheme(theme: Theme = Theme.GLOBAL) {
    when (theme) {
        Theme.GLOBAL -> {
            val flags = hideNavigationFlag or immersiveStickyFlag or drawStatusBarFlag
            clearFlags(fullScreenFlag)
            addFlags(noLimitFlag)
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                    setDecorFitsSystemWindows(false)
                    val controller = decorView.windowInsetsController
                    if (controller != null) {
                        controller.hide(WindowInsets.Type.navigationBars())
                        controller.systemBarsBehavior =
                            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                    decorView.systemUiVisibility = flags or lightStatusFlag
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                    decorView.systemUiVisibility = flags or lightStatusFlag
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    decorView.systemUiVisibility = flags
                }
                else -> {
                    decorView.systemUiVisibility = flags
                }
            }
            statusBarColor = Color.TRANSPARENT
        }

        Theme.FULL_SCREEN -> {
            val flags = noLimitFlag or hideNavigationFlag or hideStatusBar or immersiveStickyFlag
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                    setDecorFitsSystemWindows(false)
                    val controller = decorView.windowInsetsController
                    if (controller != null) {
                        if (context.isEdgeToEdgeEnabled() >= 2) {
                            controller.show(WindowInsets.Type.navigationBars())
                            controller.systemBarsBehavior =
                                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        } else {
                            controller.hide(WindowInsets.Type.navigationBars() or WindowInsets.Type.navigationBars())
                            controller.systemBarsBehavior =
                                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        }
                    }
                    addFlags(flags or lightStatusFlag)
                    decorView.systemUiVisibility = flags or lightStatusFlag
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    addFlags(flags or lightStatusFlag or keepScreenOn)
                    decorView.systemUiVisibility = flags or keepScreenOn
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    decorView.systemUiVisibility = flags
                }
                else -> {
                    decorView.systemUiVisibility = flags
                }
            }
            statusBarColor = Color.TRANSPARENT
        }
        else -> {
        }
    }
}

/**
 * 0 : Navigation is displaying with 3 buttons
 * 1 : Navigation is displaying with 2 button(Android P navigation mode)
 * 2 : Full screen gesture(Gesture on android Q)
 */
fun Context.isEdgeToEdgeEnabled(): Int {
    val resourceId = resources.getIdentifier("config_navBarInteractionMode", "integer", "android")
    return if (resourceId > 0) {
        resources.getInteger(resourceId)
    } else 0
}




fun convertIntoErrorObjet(e: ApiException): ErrorModel? {
    return e.message?.let { ErrorModel(it, e.errno, e.code) }
}
