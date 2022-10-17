package com.ascri.composebox.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ascri.composebox.R
import com.ascri.composebox.presentation.base.BaseActivity
import com.ascri.myapplicationcompose.ui.theme.MyApplicationComposeTheme

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
//        setContentView(R.layout.main_activity)
//        navHostFragment =
//            (supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment)
//        navController = navHostFragment.navController
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationComposeTheme {
        Greeting("Android")
    }
}