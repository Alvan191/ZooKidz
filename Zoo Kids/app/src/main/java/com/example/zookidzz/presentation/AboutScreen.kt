package com.example.zookidzz.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.zookidzz.R
import com.example.zookidzz.data.DataAbout
import com.example.zookidzz.data.aboutZooKidz
import com.example.zookidzz.data.rowZoo
import com.example.zookidzz.data.rowZoosec
import com.example.zookidzz.data.teamZoo

@Composable
fun AboutScreen(
    modifier: Modifier,
    aboutZookidz: List<aboutZooKidz> = DataAbout.dataPengenalan,
    aboutRowzoo: List<rowZoo> = DataAbout.dataRowZooFirst,
    aboutRowzoosec: List<rowZoosec> = DataAbout.dataRowZooSecond,
    abotTeamzoo: List<teamZoo> = DataAbout.dataTeam
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, top = 20.dp)
        ){
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "About",
                modifier = Modifier
                    .size(22.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "About",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.roboto_black)),
                fontWeight = FontWeight(800),
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            items(aboutZookidz) { aboutzooKidz ->
                AboutZooKidz(
                    aboutzooKidz = aboutzooKidz
                )
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Mengapa Harus ZooKidz?",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_black)),
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 23.dp)
                )
                LazyRow(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    items(aboutRowzoo) { aboutrowZoo ->
                        RowZooItem(aboutrowZoo = aboutrowZoo)
                    }
                }
                LazyRow(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    items(aboutRowzoosec) { aboutrowZoosec ->
                        RowZooItemSec(aboutrowZoosec = aboutrowZoosec)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Team Project",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_black)),
                    fontWeight = FontWeight(300),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 23.dp)
                )
                LazyRow(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    items(abotTeamzoo) { aboutteamZoo ->
                        TeamZooItem(
                            aboutteamZoo = aboutteamZoo
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AboutZooKidz(
    aboutzooKidz: aboutZooKidz
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 22.dp)
            .padding(top = 10.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = aboutzooKidz.logo),
                    contentDescription = "Logo Image",
                    modifier = Modifier
                        .size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text =  "Selamat datang di ZooKidz - Aplikasi Pengenalan Hewan yang Menggemaskan untuk Anak-Anak!",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text =  aboutzooKidz.text,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.roboto_light)),
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun RowZooItem(
    aboutrowZoo: rowZoo
) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .width(170.dp)
            .height(200.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box (
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(id = aboutrowZoo.background),
                contentDescription = "Latar Belakang ",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.3f),
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = aboutrowZoo.text,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = aboutrowZoo.description,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun RowZooItemSec(
    aboutrowZoosec: rowZoosec
) {
    Card (
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(170.dp)
            .height(200.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box (
            modifier = Modifier.fillMaxSize()
        ){
        Image(
            painter = painterResource(id = aboutrowZoosec.background),
            contentDescription = "Latar Belakang ",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f),
            alignment = Alignment.Center
        )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = aboutrowZoosec.text,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = aboutrowZoosec.description,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun TeamZooItem(
    aboutteamZoo: teamZoo
) {
    Card(
        modifier = Modifier
            .width(145.dp)
            .height(190.dp)
            .wrapContentHeight()
            .padding(horizontal = 8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = aboutteamZoo.photo),
                    contentDescription = "Image Team",
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .height(80.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text =  aboutteamZoo.nama,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Justify
            )
        }
    }
}
