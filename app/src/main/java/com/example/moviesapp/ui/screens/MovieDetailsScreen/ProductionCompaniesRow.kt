package com.example.moviesapp.ui.screens.MovieDetailsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.domain.Model.MovieDetails.ProductionCompany
import com.example.moviesapp.ui.theme.ExtraLargePadding
import com.example.moviesapp.ui.theme.ExtraSmallPadding
import com.example.moviesapp.ui.theme.LargePadding
import com.example.moviesapp.ui.theme.PurpleGrey80
import com.example.moviesapp.ui.theme.Shapes
import com.example.moviesapp.ui.utils.ImageUtils

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@Composable
fun ProductionCompaniesRow(productionCompanies: List<ProductionCompany>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LargePadding),
        horizontalArrangement = Arrangement.spacedBy(LargePadding)
    ) {
        items(productionCompanies) { company ->
            ProductionCompaniesItem(company)
        }
    }
}

@Composable
fun ProductionCompaniesItem(company: ProductionCompany) {

    Surface (
        border = BorderStroke(width = ExtraSmallPadding, color = PurpleGrey80),
        shape = Shapes.medium,
        color = Color.Transparent
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(company.logoPath?.let {
                    ImageUtils.getFullImageUrl(
                        it
                    )
                }),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp, 50.dp)
            )
            Text(text = company.name ?: "", style = TextStyle(fontSize = 12.sp))
        }
    }
}