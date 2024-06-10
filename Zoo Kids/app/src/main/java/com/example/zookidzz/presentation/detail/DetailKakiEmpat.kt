package com.example.zookidzz.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.zookidzz.data.BerkakiDua
import com.example.zookidzz.data.BerkakiEmpat
import com.example.zookidzz.data.DataSaya

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKakiEmpat(
    modifier: Modifier = Modifier,
    navController: NavController,
    itemId: Int?
) { Column {
    TopAppBar(
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back"
                )
            }
        }, title = {
            Text(text = "Detail Column Screen")
        }
    )
    val newKaki4List = DataSaya.datKaki4.filter { item ->
        item.id == itemId
    }
    Column(
        modifier = modifier
    ) {
        DetailHewan(newKaki4List, modifier)
    }
}
}


@Composable
private fun DetailHewan(
    newKaki4List: List<BerkakiEmpat>,
    modifier: Modifier
) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = newKaki4List[0].photo)
                        .build(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(height = 250.dp, width = 170.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = "Poster Movie"
                )
            }
            Text(
                text = newKaki4List[0].name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Spacer(modifier = Modifier.width(25.dp))
    Column(modifier = Modifier.padding(4.dp)) {
        Text(
            text = "(${newKaki4List[0].nickname})",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Informasi : ${newKaki4List[0].informasi}",
            fontSize = 16.sp
        )
    }
}
