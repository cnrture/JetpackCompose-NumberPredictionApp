package com.canerture.numberpredictionapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import numberpredictionapp.R

@Composable
fun ResultScreen(gelenSonuc: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {

        if (gelenSonuc) {
            Text(
                text = "Win",
                color = colorResource(id = R.color.green),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = R.drawable.happy_picture),
                contentDescription = "Happy Picture"
            )
        }   else {
            Text(
                text = "Lose",
                color = colorResource(id = R.color.green),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = R.drawable.sad_picture),
                contentDescription = "Sad Picture"
            )
        }


    }
}