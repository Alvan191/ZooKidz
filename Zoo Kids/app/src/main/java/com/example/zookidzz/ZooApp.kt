package com.example.zookidzz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zookidzz.login.LoginScreen
import com.example.zookidzz.login.RegistrasiScreen
import com.example.zookidzz.navigation.NavigationItem
import com.example.zookidzz.navigation.Screen
import com.example.zookidzz.presentation.AboutScreen
import com.example.zookidzz.presentation.BookmarkScreen
import com.example.zookidzz.presentation.HomeScreen
import com.example.zookidzz.presentation.NotesScreen
import com.example.zookidzz.presentation.component.AddNotesScreen
import com.example.zookidzz.presentation.detail.DetailKakiDua
import com.example.zookidzz.presentation.detail.DetailKakiEmpat
import com.example.zookidzz.presentation.detail.DetailKakiEnam
import com.example.zookidzz.presentation.detail.DetailNotesScreen
import com.example.zookidzz.utils.shouldShowBottomBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZooApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = currentRoute.shouldShowBottomBar()
            ) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = modifier.padding(contentPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Register.route) {
                RegistrasiScreen(navController)
            }
            composable(Screen.ZooScreen.route){
                ZooApp(modifier = Modifier)
            }
            composable(Screen.Home.route) {
                HomeScreen(modifier, navController)
            }
            composable(Screen.Notes.route) {
                NotesScreen(modifier, navController)
            }
            composable(Screen.Bookmark.route) {
                BookmarkScreen(navController)
            }
            composable(Screen.About.route) {
                AboutScreen(modifier)
            }

            composable(
                Screen.DetailKakiDuasc.route + "/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailKakiDua(
                    navController = navController,
                    itemId = navBackStackEntry.arguments?.getInt("itemId")
                )
            }
            composable(
                Screen.DetailKakiEmpatsc.route + "/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailKakiEmpat(
                    navController = navController,
                    itemId = navBackStackEntry.arguments?.getInt("itemId")
                )
            }
            composable(
                Screen.DetailKakiEnamsc.route + "/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailKakiEnam(
                    navController = navController,
                    itemId = navBackStackEntry.arguments?.getInt("itemId")
                )
            }
            composable(Screen.AddNotesc.route){
                AddNotesScreen(navController)
            }

            composable(
                route = Screen.DetailNotess.route,
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType }
                )
            ) {
                val titleFile = it.arguments?.getString("title") ?: ""
                val descFile = it.arguments?.getString("desc") ?: ""

                DetailNotesScreen(
                    titleFile = titleFile,
                    descFile = descFile,
                    navController = navController
                )
            }
        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xFF3D4E79)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Notes",
                icon = Icons.Default.NoteAlt,
                screen = Screen.Notes
            ),
            NavigationItem(
                title = "Bookmark",
                icon = Icons.Default.Bookmarks,
                screen = Screen.Bookmark
            ),
            NavigationItem(
                title = "About",
                icon = Icons.Default.Info,
                screen = Screen.About
            )
        )
        navigationItems.map { item ->
            AnimatedVisibility(
                visible = currentRoute == item.screen.route,
                enter = slideInVertically(initialOffsetY ={ it },
                    animationSpec = tween(500, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(targetOffsetY = { -it },
                    animationSpec = tween(500, easing = LinearOutSlowInEasing)
                )
            ) {

            }
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.screen.route) Color(0xFF3D4E79) else Color(0xFFB0BEC5)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.screen.route) Color.White else Color(0xFFB0BEC5),
                        fontWeight = if (currentRoute == item.screen.route) FontWeight.Bold else FontWeight.Normal,
                        fontSize = if (currentRoute == item.screen.route) 14.sp else 12.sp
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ZooAppPreview() {
    ZooApp()
}