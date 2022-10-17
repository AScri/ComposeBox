package com.ascri.cleanapp.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ascri.cleanapp.R
import com.ascri.cleanapp.presentation.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment)
        navController = navHostFragment.navController
    }
}