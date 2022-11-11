package com.ascri.composebox.presentation.flow.architecture_example

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ascri.composebox.R
import com.ascri.composebox.presentation.MainPreview
import com.ascri.composebox.presentation.base.BaseViewState
import com.ascri.composebox.presentation.base.getData
import com.ascri.composebox.utils.helpers.DevicePreviews
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CounterScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CounterScreenBody(modifier, uiState,
        onIncrementClick = {
            viewModel.onEvent(CounterEvent.IncrementCounter)
        }, onDecrementClick = {
            viewModel.onEvent(CounterEvent.DecrementCounter)
        },
        onNavigateBack = { navController.popBackStack() }
    )
}

@Composable
fun CounterScreenBody(
    modifier: Modifier = Modifier,
    uiState: BaseViewState<CounterState>,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.Black,
                title = { androidx.compose.material.Text(stringResource(R.string.counter)) },
                navigationIcon = {
                    Image(
                        painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(enabled = true) {
                                onNavigateBack.invoke()
                            }
                            .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                    )
                }
            )
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    val result = when (uiState) {
                        is BaseViewState.Data -> uiState.getData().counter
                        is BaseViewState.Empty -> "Empty"
                        is BaseViewState.Error -> "Error"
                        is BaseViewState.Loading -> "Loading"
                    }
                    Text(text = "Counter: $result")
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { onDecrementClick.invoke() }) {
                            Text(text = "-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { onIncrementClick.invoke() }) {
                            Text(text = "+")
                        }
                    }
                }
            }
        }
    )
}

//@DevicePreviews
@Preview
@Composable
fun CounterScreenPopulated() {
    MainPreview {
        CounterScreenBody(
            modifier = Modifier.fillMaxSize(),
            uiState = BaseViewState.Data(CounterState()),
            onIncrementClick = {},
            onDecrementClick = {},
            onNavigateBack = {}
        )
    }
}