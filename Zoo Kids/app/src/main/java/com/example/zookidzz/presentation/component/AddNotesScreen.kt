package com.example.zookidzz.presentation.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        header = "Buat Catatan",
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
                            contentDescription = "Panah Kembali"
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
            if (!readOnly) {
                CostumTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = "Judul",
                    readOnly = readOnly
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            CostumTextField(
                value = desc,
                onValueChange = onDescChange,
                label = if (!readOnly) "Hal Apa Yang Kamu Pikirkan?" else "",
                readOnly = readOnly,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (!readOnly) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onSaveFileClick,
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF3D4E79)
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(text = "Simpan")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    readOnly: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            TextField(
                value = value,
                onValueChange = onValueChange,
                readOnly = readOnly,
                modifier = modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}