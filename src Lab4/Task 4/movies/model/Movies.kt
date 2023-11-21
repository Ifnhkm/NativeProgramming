package com.example.movies.model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movies(
    @StringRes val stringResourceId: Int,
    val title: Int, val day: Int,
    @DrawableRes val imageResourceId: Int
)
