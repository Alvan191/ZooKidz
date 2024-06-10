package com.example.zookidzz.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NotesScreen() {
    Text(
        text = "Halaman Catatan Pengguna",
        fontSize = 30.sp,
        fontWeight = FontWeight(600)
    )
}