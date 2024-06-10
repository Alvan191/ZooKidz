package com.example.zookidzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zookidzz.login.RegistrasiScreen
import com.example.zookidzz.login.LoginScreen
import com.example.zookidzz.navigation.Screen
import com.example.zookidzz.presentation.HomeScreen
import com.example.zookidzz.ui.theme.ZooKidzzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZooKidzzTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route,
                    builder = {
                        composable(Screen.Login.route) {
                            LoginScreen(navController)
                        }
                        composable(Screen.Register.route) {
                            RegistrasiScreen(navController)
                        }
                        composable(Screen.ZooScreen.route){
                            ZooApp(modifier = Modifier)
                        }
                        composable(Screen.Home.route){
                            HomeScreen(modifier = Modifier, navController)
                        }
                    }
                )
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ZooKidzzTheme {
//        Greeting("Android")
//    }
//}