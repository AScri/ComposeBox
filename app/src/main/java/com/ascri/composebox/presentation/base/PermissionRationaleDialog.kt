package com.ascri.composebox.presentation.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.activity.ComponentDialog
import androidx.activity.addCallback
import com.ascri.composebox.databinding.PermissionRationaleDialogBinding

class PermissionRationaleDialog : BaseDialogFragment() {
    private lateinit var binding: PermissionRationaleDialogBinding
    var onRetry: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = PermissionRationaleDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = ComponentDialog(requireContext(), theme).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(false)
            setContentView(binding.root)
            onBackPressedDispatcher
                .addCallback(this) {}
        }
        setUpViews()
        return dialog
    }

    override fun setUpViews() {
        binding.btnRetry.setOnClickListener {
            onRetry?.invoke()
            dismiss()
        }
        binding.btnCancel.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        this.dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
