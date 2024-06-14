package com.example.zookidzz.map

import android.content.pm.PackageManager
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.zookidzz.R
import com.example.zookidzz.map.MapTypeOption
import com.example.zookidzz.ui.theme.ZooKidzzTheme
import com.example.zookidzz.utils.getResizedBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    MapsContent(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsContent(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL, isMyLocationEnabled = false)) }
    var selectedMapTypeOption by remember { mutableStateOf(MapTypeOption.NORMAL) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Maps") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Icon Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Options"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        ) {
                        MapTypeOption.entries.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option.name) },
                                onClick = {
                                    selectedMapTypeOption = option
                                    expanded = false
                                    properties = when (selectedMapTypeOption){
                                        MapTypeOption.NORMAL -> MapProperties(mapType = MapType.NORMAL)
                                        MapTypeOption.TERRAIN -> MapProperties(mapType = MapType.TERRAIN)
                                        MapTypeOption.SATELLITE -> MapProperties(mapType = MapType.SATELLITE)
                                        MapTypeOption.HYBRID -> MapProperties(mapType = MapType.HYBRID)

                                    }
                                }
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
    ) {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ){
            val context = LocalContext.current
            val zooKidzz = LatLng(-6.368623221764378, 106.95991741074657)
            val cameraPositionState = rememberCameraPositionState{
                position = CameraPosition.fromLatLngZoom(zooKidzz, 18f)
            }
            val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true, myLocationButtonEnabled = true)) }
            val iconBitmap = getResizedBitmap(context, R.drawable.logo, 32, 32)
            val requestPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted){
                    properties = properties.copy(isMyLocationEnabled = true)
                }
            }

            fun checkLocationPermission() {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    properties = properties.copy(isMyLocationEnabled = true)
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }

            LaunchedEffect(Unit) {
                checkLocationPermission()
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            ){
                MarkerInfoWindow(
                    state = MarkerState(zooKidzz),
                    icon = BitmapDescriptorFactory.fromBitmap(iconBitmap)
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.LightGray)
                            .padding(24.dp)
                    ){
                        Text(text = "Zoo Kidz", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
private fun MapsScreenPreview() {
    ZooKidzzTheme {
        MapsScreen(navController = rememberNavController(), modifier = Modifier)
    }
}