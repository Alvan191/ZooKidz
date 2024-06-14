package com.example.zookidzz.navigation

sealed class Screen (val route: String){
    data object Onboarding: Screen("onboarding")
    data object Login: Screen("login")
    data object Register: Screen("register")
    data object ZooScreen: Screen("Zoo")
    data object Home: Screen("home")
    data object Notes: Screen("catatan")
    data object Bookmark: Screen("penanda")
    data object About: Screen("about")
    data object DetailKakiDuasc: Screen("detail_kaki_dua")
    data object DetailKakiEmpatsc: Screen("detail_kaki_empat")
    data object DetailKakiEnamsc: Screen("detail_kaki_enam")
    data object AddNotesc: Screen("Catatan")
    data object DetailNotess: Screen("detail_task/{title}/{desc}") {
        fun createRoute(title: String, desc: String) = "detail_task/$title/$desc"
    }
}