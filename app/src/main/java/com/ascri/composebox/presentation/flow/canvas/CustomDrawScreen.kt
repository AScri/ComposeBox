package com.ascri.composebox.presentation.flow.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ascri.composebox.presentation.MainPreview

@Composable
fun CustomDrawScreen(navController: NavController){
    CustomDrawScreenBody()
}

@Composable
fun CustomDrawScreenBody(
    modifier: Modifier = Modifier,
    minSize: DpSize = DpSize(32.dp, 32.dp),
) {
    Canvas(modifier = modifier.size(minSize)) {
        val b = Brush.radialGradient(
            colors = listOf(
                Color.Red, Color.Black
            )
        )
        drawCircle(b)
    }
}

@Preview
@Composable
fun CustomDrawScreenPopulate() {
    MainPreview { CustomDrawScreenBody() }
}