package com.example.zookidzz.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import androidx.navigation.NavController
import com.example.zookidzz.R
import com.example.zookidzz.data.BerkakiDua
import com.example.zookidzz.data.BerkakiEmpat
import com.example.zookidzz.data.BerkakiEnam
import com.example.zookidzz.data.DataSaya
import com.example.zookidzz.data.DataStore
import com.example.zookidzz.data.SharedPreferencesManager
import com.example.zookidzz.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    hewanKaki4: List<BerkakiEmpat> = DataSaya.datKaki4,
    hewanKaki2: List<BerkakiDua> = DataSaya.datKaki2,
    hewanKaki6: List<BerkakiEnam> = DataSaya.datKaki6
) {
    // Menerapkan SharedPreferences
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val dataStore = DataStore(context)
    val email = sharedPreferencesManager.email ?: ""

    //fungsi list kategori row
    var selectedCategory by remember { mutableStateOf("Semua") }
    var showFlyingAnimals by remember { mutableStateOf(false) }
    var showNonFlyingAnimals by remember { mutableStateOf(false) }
    val categories = listOf("Semua", "Berkaki Dua", "Berkaki Empat", "Berkaki Enam", "Terbang", "Tidak Terbang")

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = "ZooKidz",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ){
            Text(
                text = buildAnnotatedString {
                    append("Hi, ")
                    val emailPrefix = email.split("@")[0]
                    withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                        append(emailPrefix + "😍")
                    }
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                )
            )
            IconButton(
                onClick = {
                    sharedPreferencesManager.clear()
                    coroutineScope.launch {
                        dataStore.clearStatus()
                    }
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout"
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 1.dp)
                .background(
                    color = Color(0xFFD8D8D8),
                )
        )

        Spacer(modifier = Modifier.height(10.dp))
        CategoryRow(categories, selectedCategory) { category ->
            selectedCategory = category
            showFlyingAnimals = category == "Terbang"
            showNonFlyingAnimals = category == "Tidak Terbang"
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(contentPadding = PaddingValues(vertical = 10.dp)) {
            val itemsToShow = when {
                showFlyingAnimals -> {
                    (hewanKaki2.filter { it.canFly } +
                            hewanKaki4.filter { it.canFly } +
                            hewanKaki6.filter { it.canFly }).distinctBy { it }
                }
                showNonFlyingAnimals -> {
                    (hewanKaki2.filter { !it.canFly } +
                            hewanKaki4.filter { !it.canFly } +
                            hewanKaki6.filter { !it.canFly }).distinctBy { it }
                }
                selectedCategory == "Berkaki Dua" -> hewanKaki2.map { it }
                selectedCategory == "Berkaki Empat" -> hewanKaki4.map { it }
                selectedCategory == "Berkaki Enam" -> hewanKaki6.map { it }
                else -> hewanKaki2.map { it } + hewanKaki4.map { it } + hewanKaki6.map { it }
            }
            items(itemsToShow) { item ->
                when (item) {
                    is BerkakiDua -> BerkakiDuaItem(item, Modifier) { navController.navigate(Screen.DetailKakiDuasc.route + "/${item.id}") }
                    is BerkakiEmpat -> BerkakiEmpatItem(item, Modifier) { navController.navigate(Screen.DetailKakiEmpatsc.route + "/${item.id}") }
                    is BerkakiEnam -> BerkakiEnamItem(item, Modifier) { navController.navigate(Screen.DetailKakiEnamsc.route + "/${item.id}") }
                }
            }
        }
    }
}

@Composable
fun CategoryRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            val backgroundColor = if (isSelected) Color(0xFF3D4E79) else Color(0xFFEEEEEE)
            val textColor = if (isSelected) Color.White else Color.Black

            Text(
                text = category,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(backgroundColor)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clickable { onCategorySelected(category) },
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BerkakiDuaItem(berkakiDua: BerkakiDua, modifier: Modifier, onClick: () -> Unit) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier
                .padding(10.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = berkakiDua.photo),
                    contentDescription = berkakiDua.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .size(width = 150.dp, height = 100.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = berkakiDua.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                IconButton(
                    onClick = { isBookmarked = !isBookmarked },
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) Color(0xFF3D4E79) else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun BerkakiEmpatItem(berkakiEmpat: BerkakiEmpat, modifier: Modifier, onClick: () -> Unit) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier
                .padding(10.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = berkakiEmpat.photo),
                    contentDescription = berkakiEmpat.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .size(width = 150.dp, height = 100.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = berkakiEmpat.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                IconButton(
                    onClick = { isBookmarked = !isBookmarked },
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) Color(0xFF3D4E79) else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun BerkakiEnamItem(berkakiEnam: BerkakiEnam, modifier: Modifier, onClick: () -> Unit) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(3f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier
                .padding(10.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = berkakiEnam.photo),
                    contentDescription = berkakiEnam.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .size(width = 150.dp, height = 100.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = berkakiEnam.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                IconButton(
                    onClick = { isBookmarked = !isBookmarked },
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) Color(0xFF3D4E79) else Color.Gray
                    )
                }
            }
        }
    }
}