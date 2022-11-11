package com.ascri.composebox.presentation.flow.click

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ascri.composebox.R
import com.ascri.composebox.presentation.MainPreview

@Composable
fun ClickScreen(navController: NavController){
    ClickScreenBody()
}


@Composable
fun ClickScreenBody() {
    val count = remember {
        mutableStateOf(value = 1)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.item_count) + " ${count.value}",
            fontSize = 24.sp,
            color = Color.Black
        )
        // button with rounded corners
        Button(
            onClick = {
                count.value += 1
            },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.plus))
        }
        Button(
            onClick = {
                count.value -= 1
            },
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.minus))
        }
    }
}

@Preview
@Composable
fun CustomDrawScreenPopulate() {
    MainPreview { ClickScreenBody() }
}