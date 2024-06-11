package com.example.zookidzz.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zookidzz.R
import com.example.zookidzz.data.DataStore
import com.example.zookidzz.data.SharedPreferencesManager
import com.example.zookidzz.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val dataStore = DataStore(context)

    val statusLoggedIn = dataStore.getStatusLogin.collectAsState(initial = false)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var katasandiVisible by remember { mutableStateOf(false) }

    if (statusLoggedIn.value) {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Login.route) {
                inclusive = true
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,

            ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Image",
                    modifier = Modifier
                        .size(130.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Masuk",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_black)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Masukkan email dan kata sandi dengan benar",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = "E-mail",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFAEAEAE)
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAEAEAE),
                    unfocusedBorderColor = Color(0xFFAEAEAE),
                    containerColor = Color(0xFFF5F5F5),
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(
                        text = "Kata Sandi",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFAEAEAE),
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFAEAEAE),
                    unfocusedBorderColor = Color(0xFFAEAEAE),
                    containerColor = Color(0xFFF5F5F5),
                ),
                visualTransformation = if (katasandiVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (katasandiVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (katasandiVisible) "Hide password" else "Show password"

                    IconButton(onClick = { katasandiVisible = !katasandiVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Lupa Kata Sandi?",
                    modifier = Modifier.clickable { },
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_light)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(color = Color(0xFF3D4E79), shape = RoundedCornerShape(size = 5.dp))
                    .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3D4E79)
                ),
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(
                            context,
                            "Email dan Password Wajib Diisi",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        sharedPreferencesManager.email = email
                        sharedPreferencesManager.password = password
                        coroutineScope.launch {
                            dataStore.saveStatus(true)
                        }
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                },
            ) {
                Text(
                    text = "Masuk",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tidak punya akun? ",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_light)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Daftar",
                    modifier = Modifier.clickable { navController.navigate(Screen.Register.route) },
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF0097B2),
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}