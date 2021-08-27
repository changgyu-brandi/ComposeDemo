package com.changgyu.composedemo.demo_project.home.rank_tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.changgyu.composedemo.R

@Composable
fun RankTabScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun RankTabScreenPreview() {
    RankTabScreen()
}