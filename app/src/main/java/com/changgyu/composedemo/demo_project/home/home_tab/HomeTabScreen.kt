package com.changgyu.composedemo.demo_project.home.home_tab

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.changgyu.composedemo.R
import com.changgyu.composedemo.demo_project.AutoScrollingLazyRow
import com.changgyu.composedemo.demo_project.PagerSampleItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.koduok.compose.glideimage.GlideImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTabScreen() {
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
/*            Box(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentAlignment = Alignment.Center
            ) {
               Image(
                    painter = rememberImagePainter(
                        data = "https://source.unsplash.com/random",
                        builder = {
                            crossfade(500)
                            placeholder(R.drawable.ic_baseline_panorama_24)
                            //transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Text(text = "${index}", fontSize = 30.sp, textAlign = TextAlign.Center)

                GlideImage(url = "https://source.unsplash.com/pGM4sjt_BdQ")
                Text(text = "${index}/${pagerState.pageCount} 페이지",
                    modifier = Modifier.align(Alignment.BottomEnd),
                    color = Color.Black)
            }*/

        }

/*        LaunchedEffect(pagerState.currentPage) {
            launch {
                delay(2000)
                with(pagerState) {
                    val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
                    animateScrollToPage(
                        page = target,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )

                }
            }
        }*/
    }

}

@Preview(showBackground = true)
@Composable
fun HomeTabScreenPreview() {
    HomeTabScreen()
}