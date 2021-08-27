package com.changgyu.composedemo.demo_project.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.changgyu.composedemo.R
import com.changgyu.composedemo.demo_project.PagerSampleItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun CategoryScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        val pagerState = rememberPagerState(
            pageCount = 5,
            initialOffscreenLimit = 5,
            infiniteLoop = true,
            initialPage = 0
        )
        val iamgeList = listOf("" +
                "https://source.unsplash.com/random")
        HorizontalPager(
            state = pagerState
        ) { index ->
            PagerSampleItem(
                page = index,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

        }

    }

}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}