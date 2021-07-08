package com.canerture.numberpredictionapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import numberpredictionapp.R
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

@Composable
fun PredictionScreen(navController: NavController) {

    val tfPrediction = remember { mutableStateOf("") }
    val remainingRight = remember { mutableStateOf(5) }
    val helper = remember { mutableStateOf("") }
    val randomNumber = remember { mutableStateOf(0) }
    val snackbarVisibleState = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        randomNumber.value = Random.nextInt(101)
    }

    if (snackbarVisibleState.value) {
        Snackbar(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "You did not enter a number !"
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,) {
        Text(
            text = "Remaining Right : ${remainingRight.value}",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.green)
        )

        Text(
            text = "Helper : ${helper.value}",
            fontSize = 24.sp,
            color = colorResource(id = R.color.green)
        )

        OutlinedTextField(
            value = tfPrediction.value,
            onValueChange = { tfPrediction.value = it },
            label = { Text(text = "Prediction", color = colorResource(id = R.color.green))},
            textStyle = TextStyle(color = colorResource(id = R.color.green)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.green),
                unfocusedBorderColor = colorResource(id = R.color.green))
        )

        Button(
            onClick = {
                if (tfPrediction.value != "") {

                    snackbarVisibleState.value = false

                    if (remainingRight.value != 0) {

                        remainingRight.value = remainingRight.value - 1

                        val prediction = tfPrediction.value.toInt()

                        if (prediction == randomNumber.value) {
                            navController.navigate("result_screen/true") {
                                popUpTo("prediction_screen") { inclusive = true }
                            }
                            return@Button
                        }

                        if (prediction > randomNumber.value) {
                            helper.value = "Decrease"
                        }

                        if (prediction < randomNumber.value) {
                            helper.value = "Increase"
                        }

                        if (remainingRight.value == 0) {
                            navController.navigate("result_screen/false") {
                                popUpTo("prediction_screen") { inclusive = true }
                            }
                        }

                    }

                    snackbarVisibleState.value = false

                }   else {
                    snackbarVisibleState.value = true
                }
                      },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.yellow))

        ) {

            Text(
                text = "Let's Prediction",
                color = colorResource(id = R.color.green),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}