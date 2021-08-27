package com.changgyu.composedemo.demo_project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTab() {
    val tabData = listOf(
        "홈" ,
        "랭크" ,
        "라이프" ,
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
    Column{
        ScrollableTabRow(
            backgroundColor = Color.Transparent,
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp,
            modifier = Modifier.wrapContentWidth()
        ) {
            tabData.forEachIndexed { index, data ->
                Surface(
 /*                   border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(80.dp),
*/
                ) {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tabData[index],
                )
            }
        }
    }
}


@Preview
@Composable
fun MyTabPreview() {
    HomeTab()
}