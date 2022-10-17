package com.ascri.composebox.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialogFragment : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    abstract fun setUpViews()

    fun show(manager: FragmentManager) = super.show(manager, this::class.java.simpleName)

    override fun dismiss() = super.dismissAllowingStateLoss()

}