package com.ascri.composebox.presentation.flow.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.ascri.composebox.R
import com.ascri.composebox.presentation.navigation.domain.model.app.ListExample
import com.ascri.composebox.presentation.MainPreview
import java.util.*

@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun ListScreen(navController: NavController){
    ListScreenBody {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun ListScreenBody(onBackClick: () -> Unit) {
    val someList = remember {
        mutableStateListOf<ListExample>()
    }
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Image(
            painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24), contentDescription = "",
            modifier = Modifier
                .clickable(enabled = true) {
                    onBackClick.invoke()
                }
                .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {
            TextField(
                value = inputValue.value,
                onValueChange = {
                    inputValue.value = it
                },
                modifier = Modifier.weight(0.8f),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                placeholder = { Text(text = "Enter your text") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Color.Black, fontSize = TextUnit.Unspecified,
                    fontFamily = FontFamily.SansSerif
                ),
                maxLines = 1,
                singleLine = true
            )
            Button(
                onClick = {
                    someList.add(ListExample(UUID.randomUUID().toString(), inputValue.value.text))
                    keyboardController?.hide()
                },
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight()
            ) {
                Text(text = "Add")
            }
        }
        Spacer(modifier = Modifier.height(Dp(1f)))

        LazyColumn {
            items(someList, { notesList: ListExample -> notesList.id }) { item ->
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    someList.remove(item)
                }
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = Dp(1f)),
                    directions = setOf(
                        DismissDirection.EndToStart
                    ),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                    },
                    background = {
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.Default -> Color.White
                                else -> Color.Red
                            }
                        )
                        val alignment = Alignment.CenterEnd
                        val icon = Icons.Default.Delete

                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = Dp(20f)),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                icon,
                                contentDescription = "Delete Icon",
                                modifier = Modifier.scale(scale)
                            )
                        }
                    },
                    dismissContent = {
                        Card(
                            elevation = animateDpAsState(
                                if (dismissState.dismissDirection != null) 4.dp else 0.dp
                            ).value,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(alignment = Alignment.CenterVertically)
                                .padding(8.dp),
                            backgroundColor = Color.Yellow
                        ) {
                            ListItemHolder(item = item)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ListItemHolder(item: ListExample) {
    Text(
        text = item.name,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(12.dp)
    )
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Preview
@Composable
fun CustomDrawScreenPopulate() {
    MainPreview { ListScreenBody {} }
}