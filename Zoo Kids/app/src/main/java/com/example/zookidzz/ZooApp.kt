package com.example.zookidzz

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zookidzz.navigation.NavigationItem
import com.example.zookidzz.navigation.Screen
import com.example.zookidzz.presentation.AboutScreen
import com.example.zookidzz.presentation.BookmarkScreen
import com.example.zookidzz.presentation.HomeScreen
import com.example.zookidzz.presentation.NotesScreen
import com.example.zookidzz.presentation.detail.DetailKakiDua
import com.example.zookidzz.presentation.detail.DetailKakiEmpat
import com.example.zookidzz.presentation.detail.DetailKakiEnam


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZooApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute != null &&
                !currentRoute.startsWith(Screen.DetailKakiDuasc.route) &&
                currentRoute != Screen.DetailKakiEmpatsc.route &&
                currentRoute != Screen.DetailKakiEnamsc.route
            ) {
                BottomBar(navController, modifier)
            }
        },
        modifier = modifier
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(contentPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier, navController)
            }
            composable(Screen.Notes.route) {
                NotesScreen()
            }
            composable(Screen.Bookmark.route) {
                BookmarkScreen()
            }
            composable(Screen.About.route) {
                AboutScreen()
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

@Preview
@Composable
private fun ZooAppPreview() {
    ZooApp()
}