package com.vinaymj.bowlingscore.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinaymj.bowlingscore.R
import com.vinaymj.bowlingscore.domain.FrameScores
import com.vinaymj.bowlingscore.ui.theme.Purple700


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val gameScore by viewModel.scoreState.collectAsState()
    Column(horizontalAlignment = CenterHorizontally) {
        ResultSection(gameScore, modifier)

        FramesSection(gameScore, modifier)

        InputFieldSection(gameScore, modifier, viewModel)
    }

}

@Composable
fun ResultSection(gameScore: ScoreUiState, modifier: Modifier) {
    Text(text = stringResource(
        R.string.result_string,
        gameScore.frames[10]?.gameTotal ?: ""),
        modifier = modifier
            .padding(8.dp,16.dp,8.dp,0.dp)
            .testTag("matchResult"),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}
@Composable
fun FramesSection(gameScore: ScoreUiState, modifier: Modifier) {

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(arrayOf(1,2,3,4,5,6,7,8,9,10)) { _, item ->
                AddFrame(gameScore, modifier, item)
            }
        }
    }
}


@Composable
fun AddFrame(gameScore: ScoreUiState, modifier: Modifier, i: Int) {
    Row(modifier = modifier.padding(0.dp,8.dp,0.dp,0.dp)) {
        Column(
            modifier = modifier
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                .border(1.dp, Color.Black, RectangleShape)
        ) {
            DrawRollPoint(gameScore, modifier, i)
        }
    }
}

@Composable
fun DrawRollPoint(gameScore: ScoreUiState, modifier: Modifier, i: Int) {
    Row(horizontalArrangement = Arrangement.Center) {
        Text(text = stringResource(R.string.frame,i.toString()),
            modifier = modifier
                .padding(8.dp)
                .testTag("frame$i"),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center)
    }
    Row {
        Column(
            modifier = modifier.
                border(1.dp, Color.Black, RectangleShape),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(text = if(gameScore.frames[i] != null)
                gameScore.frames[i]?.first.toString() else "",
                modifier = modifier
                    .padding(16.dp,8.dp,16.dp,8.dp)
                    .testTag("first$i"),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp)
        }
        Column(
            modifier = modifier.border(1.dp, Color.Black, RectangleShape)
        ) {
            Text(text = if(gameScore.frames[i] != null)
                gameScore.frames[i]?.second.toString() else "",
                modifier = modifier
                    .padding(16.dp,8.dp,16.dp,8.dp)
                    .testTag("second$i"),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp)
        }
        if(i == 10) {
            Column(
                modifier = modifier.border(1.dp, Color.Black, RectangleShape)
            ) {
                Text(text = if(gameScore.frames[i] != null)
                    gameScore.frames[i]?.third.toString() else "",
                    modifier = modifier
                        .padding(8.dp)
                        .testTag("third$i"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp)
            }
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = if(gameScore.frames[i] != null)
            gameScore.frames[i]?.gameTotal.toString() else "",
            modifier = modifier
                .padding(8.dp)
                .testTag("gameTotal$i"),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Purple700)
    }
}


@Composable
fun InputFieldSection(gameScore: ScoreUiState, modifier: Modifier, viewModel: MainViewModel) {
    Row(modifier = modifier.padding(8.dp)) {
        var firstPoint by remember { mutableStateOf("0") }
        var secondPoint by remember { mutableStateOf("0") }
        var thirdPoint by remember { mutableStateOf("0") }
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            firstPoint = addField(gameScore, R.string.first_roll, modifier)
            secondPoint = addField(gameScore, R.string.second_roll, modifier)
            if(gameScore.frames.size == 9) {
                thirdPoint = addField(gameScore, R.string.third_roll, modifier)
            }
            Row(modifier = modifier.padding(8.dp), horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = modifier
                        .padding(8.dp, 8.dp, 24.dp, 8.dp)
                        .testTag("submitButton"),
                    onClick = {
                        viewModel.updateScore(
                            FrameScores(firstPoint.toInt(),secondPoint.toInt(),thirdPoint.toInt())
                        )
                    }) {
                    Text(text = stringResource(id = R.string.submit))
                }

                OutlinedButton(
                    modifier = modifier
                        .padding(24.dp, 8.dp, 8.dp, 8.dp)
                        .testTag("resetButton"),
                    onClick = {
                        viewModel.resetScore()
                    }) {
                    Text(text = stringResource(id = R.string.reset))
                }
            }
        }
    }
}

@Composable
private fun addField(gameScore: ScoreUiState, id: Int, modifier: Modifier): String {
    var value by remember { mutableStateOf("0") }
    val label = stringResource(id)
    OutlinedTextField(
        modifier = modifier.testTag(label),
        value = value,
        isError = gameScore.error,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { value = it },
        label = { Text(label) }
    )
    return value
}


