package com.example.zookidzz.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.zookidzz.presentation.component.Contentnotes
import com.example.zookidzz.utils.FileHelper

@Composable
fun DetailNotesScreen(
    navController: NavController,
    titleFile: String,
    descFile: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val readData = FileHelper.readFromFile(context, titleFile)
    val titleData = readData.fileName ?: ""
    val descData = readData.data ?: ""

    Contentnotes(
        header = "Detail Task",
        title = titleData,
        desc = descData,
        readOnly = true,
        onBackClick = { navController.navigateUp() },
        modifier = modifier
    )
}