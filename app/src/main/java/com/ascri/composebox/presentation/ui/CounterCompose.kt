package com.ascri.composebox.presentation.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.ascri.composebox.presentation.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun CounterCompose() {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (minus, plus, counter) = createRefs()

        val count = remember { mutableStateOf(0) }

        Box(
            modifier = Modifier
                .size(sizeOfCounterButton)
                .constrainAs(minus) {
                    end.linkTo(counter.start)
                    top.linkTo(counter.top)
                    bottom.linkTo(counter.bottom)
                }
                .background(
                    color = Yellow,
                    shape = RoundedCornerShape(roundedCorner)
                )
                .clickable {
                    count.value -= 1
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "-",
                fontSize = largeTextSize,
                color = Black,
            )
        }

        AnimatedContent(
            targetState = count.value,
            modifier = Modifier
                .constrainAs(counter) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            transitionSpec = {
                EnterTransition.None with ExitTransition.None
            }
        ) {
            Text(
                modifier = Modifier
                    .animateEnterExit(
                        enter = scaleIn(),
                        exit = scaleOut()
                    )
                    .padding(defaultMarginPadding),
                text = count.value.toString(),
                fontSize = enormousTextSize,
                color = Black
            )
        }


        Box(modifier = Modifier
            .size(sizeOfCounterButton)
            .constrainAs(plus) {
                start.linkTo(counter.end)
                top.linkTo(counter.top)
                bottom.linkTo(counter.bottom)
            }
            .background(
                color = Yellow,
                shape = RoundedCornerShape(roundedCorner)
            )
            .clickable {
                count.value += 1
            },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                fontSize = largeTextSize,
                color = Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCounterCompose() {
    CounterCompose()
}
