package com.example.moviesapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesapp.ui.utils.defaultPlaceholder

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@Composable
fun PosterPlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.defaultPlaceholder()
    )
}