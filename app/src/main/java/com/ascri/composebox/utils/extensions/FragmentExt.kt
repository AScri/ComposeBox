package com.ascri.composebox.utils.extensions

import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.ascri.composebox.presentation.common.helpers.OnBackListener
import com.google.android.material.snackbar.Snackbar

fun Fragment.getDrawableQuick(@DrawableRes drawable: Int) =
    requireContext().getDrawableQuick(drawable)

fun Fragment.getColorQuick(@ColorRes color: Int) = requireContext().getColorQuick(color)

fun Fragment.showToast(text: String) = context?.showToast(text)

fun Fragment.showToast(@StringRes textRes: Int) = context?.showToast(textRes)

fun Fragment.hideKeyboard() = activity.hideKeyboard()

fun Fragment.addBackListener(owner: LifecycleOwner, listener: OnBackListener? = null) {
    val dispatcher = requireActivity().onBackPressedDispatcher
    dispatcher.addCallback(owner, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            listener?.onBack()
        }
    })
}

fun Fragment.showSnackbar(text: String, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(requireView(), text, length).show()

fun Fragment.showSnackbar(@StringRes textRes: Int, length: Int = Snackbar.LENGTH_SHORT) =
    showSnackbar(getString(textRes), length)