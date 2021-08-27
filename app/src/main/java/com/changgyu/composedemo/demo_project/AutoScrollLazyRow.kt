package com.changgyu.composedemo.demo_project

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun <T : Any> AutoScrollingLazyRow(
    list: List<T>,
    modifier: Modifier = Modifier,
    scrollDx: Float = SCROLL_DX,
    delayBetweenScrollMs: Long = DELAY_BETWEEN_SCROLL_MS,
    divider: @Composable () -> Unit = { Spacer(modifier = Modifier.width(10.dp)) },
    itemContent: @Composable (item: T) -> Unit,
) {
    var itemsListState by remember { mutableStateOf(list) }
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        modifier = modifier,
    ) {
        items(itemsListState) {
            itemContent(item = it)
            divider()

            if (it == itemsListState.last()) {
                val currentList = itemsListState

                val secondPart = currentList.subList(0, lazyListState.firstVisibleItemIndex)
                val firstPart = currentList.subList(lazyListState.firstVisibleItemIndex, currentList.size)

                rememberCoroutineScope().launch {
                    lazyListState.scrollToItem(0, maxOf(0, lazyListState.firstVisibleItemScrollOffset - scrollDx.toInt()))
                }

                itemsListState = firstPart + secondPart
            }
        }

    }
    LaunchedEffect(Unit) {
        autoScroll(lazyListState, scrollDx, delayBetweenScrollMs)
    }
}

private tailrec suspend fun autoScroll(
    lazyListState: LazyListState,
    scrollDx: Float,
    delayBetweenScrollMs: Long,
) {
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(scrollDx)
    }
    delay(delayBetweenScrollMs)

    autoScroll(lazyListState, scrollDx, delayBetweenScrollMs)
}

private const val DELAY_BETWEEN_SCROLL_MS = 8L
private const val SCROLL_DX = 1f