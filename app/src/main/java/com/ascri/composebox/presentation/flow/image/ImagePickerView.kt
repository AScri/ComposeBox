package com.ascri.composebox.presentation.flow.image

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ImagePickerView(navController: NavController) {
    val context = LocalContext.current

    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        val source = uri?.let {
            ImageDecoder
                .createSource(context.contentResolver, it)
        }
        bitmap.value = source?.let { ImageDecoder.decodeBitmap(it) }
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Button(onClick = {
                launcher.launch("image/*")
            }, modifier = Modifier.padding(16.dp)) {
                Text(text = "Pick image")
            }
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp)) {
                Text(text = "Back")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        bitmap.value?.asImageBitmap()?.let {
            Image(
                bitmap = it, contentDescription = "",
                modifier = Modifier.size(300.dp)
            )
        }
    }


}