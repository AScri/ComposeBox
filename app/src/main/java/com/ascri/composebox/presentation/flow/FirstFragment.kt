package com.ascri.composebox.presentation.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ascri.composebox.R
import com.ascri.composebox.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : BaseFragment() {
    companion object {
        val TAG = FirstFragment::class.java.name
        fun newInstance() = FirstFragment()
    }

    private val viewModel: FirstViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

}