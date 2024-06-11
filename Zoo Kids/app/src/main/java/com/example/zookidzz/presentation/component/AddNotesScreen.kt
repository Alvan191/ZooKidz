package com.example.zookidzz.presentation.component

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zookidzz.model.FileModel
import com.example.zookidzz.utils.FileHelper

@Composable
fun AddNotesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val fileModel = FileModel()

    var title by remember {
        mutableStateOf("")
    }
    var desc by remember {
        mutableStateOf("")
    }

    Contentnotes(
        header = "Task Baru",
        title = title,
        onTitleChange = { title = it },
        desc = desc,
        onDescChange = { desc = it },
        onBackClick = { navController.navigateUp() },
        onSaveFileClick = {
            fileModel.fileName = title
            fileModel.data = desc
            FileHelper.writeToFile(fileModel, context)
            Toast.makeText(
                context,
                "Menyimpan File " + fileModel.fileName + "Berhasil",
                Toast.LENGTH_SHORT
            ).show()
            navController.navigateUp()
        },
        modifier = modifier
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contentnotes(
    header: String,
    title: String,
    desc: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit = {},
    onDescChange: (String) -> Unit = {},
    onSaveFileClick: () -> Unit = {},
    readOnly: Boolean = false,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = header) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextFieldStyle(
                value = title, onValueChange = onTitleChange,
                label = "Tugas",
                readOnly = readOnly
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextFieldStyle(
                value = desc,
                onValueChange = onDescChange,
                label = "Deskripsi",
                readOnly = readOnly,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (!readOnly)
                Button(
                    onClick = onSaveFileClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Simpan")
                }
        }
    }
}

@Composable
fun OutlinedTextFieldStyle(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    readOnly: Boolean,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        readOnly = readOnly,
        modifier = modifier.fillMaxWidth()
    )
}