package com.example.intentsejemplo2.utils

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun View.showSoftInput(): Boolean =
    requestFocus() && context.getSystemService<InputMethodManager>()?.showSoftInput(this,
        InputMethodManager.SHOW_IMPLICIT) == true

fun View.hideSoftKeyboard(): Boolean =
    context.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(windowToken, 0) == true