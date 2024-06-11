package com.example.zookidzz.utils

import com.example.zookidzz.navigation.Screen

fun String?.shouldShowBottomBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.Notes.route,
        Screen.Bookmark.route,
        Screen.About.route
    )
}