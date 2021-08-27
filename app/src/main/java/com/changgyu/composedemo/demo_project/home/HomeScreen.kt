package com.changgyu.composedemo.demo_project.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changgyu.composedemo.R
import com.changgyu.composedemo.demo_project.home.home_tab.HomeTabScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        val tabData = listOf(
            "홈",
            "랭크",
            "라이프",
            "유아"
        )

        val pagerState = rememberPagerState(
            pageCount = tabData.size,
            initialOffscreenLimit = 3,
            infiniteLoop = true,
            initialPage = 0
        )
        val tabIndex = pagerState.currentPage
        val coroutineScope = rememberCoroutineScope()
        Column {
            ScrollableTabRow(
                backgroundColor = Color.Transparent,
                selectedTabIndex = tabIndex,
                edgePadding = 0.dp,
                modifier = Modifier.wrapContentWidth()
            ) {
                tabData.forEachIndexed { index, data ->
                    Surface {
                        Tab(selected = tabIndex == index,

                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }, text = {
                                Text(text = data)
                            })
                    }

                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { index ->
                //버그인거같은데 여기서 컬럼을 빼고 실행하면 에러난다.
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    when(index){
                        0->{
                            HomeTabScreen()
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

