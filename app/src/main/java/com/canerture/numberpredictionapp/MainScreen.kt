package com.canerture.numberpredictionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.canerture.numberpredictionapp.ui.theme.NumberPredictionAppTheme
import numberpredictionapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberPredictionAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PageTransition()
                }
            }
        }
    }
}

@Composable
fun PageTransition() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main_screen") {

        composable("main_screen"){
            MainScreen(navController)
        }

        composable("prediction_screen"){
            PredictionScreen(navController)
        }

        composable("result_screen/{result}",
            arguments = listOf(
            navArgument("result"){ type = NavType.BoolType }
        )){
            val result = it.arguments?.getBoolean("result")!!
            ResultScreen(result)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = "Prediction Game",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.green)
        )

        Image(
            painter = painterResource(id = R.drawable.dice_picture),
            contentDescription = "Dice Picture"
        )

        Button(
            onClick = { navController.navigate("prediction_screen") },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.yellow))
        ) {
            Text(
                text = "LET'S PLAY",
                color = colorResource(id = R.color.green),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberPredictionAppTheme {

    }
}