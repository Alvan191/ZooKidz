package com.example.zookidzz.login

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zookidzz.R
import com.example.zookidzz.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Onboarding(navController: NavController) {
    var animationCompleted by remember { mutableStateOf(false) }
    var textSizeState by remember { mutableStateOf(60.dp) }
    val textSizeAnimatable = remember { Animatable(initialValue = 20f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val targetValue = 100.dp
        textSizeAnimatable.animateTo(
            targetValue.value,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
        animationCompleted = true
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Image",
            modifier = Modifier
                .size(textSizeAnimatable.value.dp)
        )

        if (animationCompleted) {
            scope.launch {
                navController.navigate(Screen.Login.route)
            }
        }
    }
}

@Preview
@Composable
private fun OnboardingPreview() {
    Onboarding(navController = NavController(context = LocalContext.current))
}