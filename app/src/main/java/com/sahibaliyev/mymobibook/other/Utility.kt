package com.sahibaliyev.mymobibook.other

import android.view.Window
import androidx.core.view.WindowCompat

fun fullScreen(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window,false)
}