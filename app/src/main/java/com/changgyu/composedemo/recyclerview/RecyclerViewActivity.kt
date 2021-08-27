package com.changgyu.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changgyu.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch
import java.util.*

class RecyclerViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mNames: ArrayList<Name> = arrayListOf()
        for (i in 0..100) mNames.add(Name("default ${i}", false))
        setContent {
            MyApp {
                RecyclerViewTest(mNames)
            }
        }
    }

}



@Composable
fun RecyclerViewTest(mNames: ArrayList<Name>) {

    val count = remember { mutableStateOf(mNames.size - 1) }
    val listState = rememberLazyListState()
    val composableScope = rememberCoroutineScope()
    Column(modifier = Modifier.height(500.dp)) {

        NameList(names = mNames, modifier = Modifier.weight(1f), listState = listState)
        Divider(color = Color.Blue, thickness = 32.dp)
        Counter(count.value) {
            count.value = it
            mNames.add(Name("added ${count.value}", false))
            composableScope.launch {
                listState.animateScrollToItem(index = mNames.size)
            }
        }
    }
}

@Composable
fun NameList(names: ArrayList<Name>, modifier: Modifier, listState: LazyListState) {
    LazyColumn(modifier = modifier, state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        items(names) {
            NameItem(data = it)
            Divider(color = Color.Black)
        }
    }

}

@Composable
fun NameItem(data: Name) {
    var isSelected by remember { mutableStateOf(data.isSelected)}
    val mColor by animateColorAsState(if(isSelected) Color.White else Color.Yellow)
    Surface(
        color = mColor,
        border = BorderStroke(5.dp, MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp
/*
        , onClick = { isSelected = !isSelected
            data.isSelected = isSelected } //현재 실험 버전
*/
    ) {
        Text(text = "${data.name}!",
            fontSize = 20.sp,
            modifier = Modifier
            .padding(15.dp)
            .clickable(onClick = {
                isSelected = !isSelected
                data.isSelected = isSelected
            })
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "${count} times clicked")
    }
}

data class Name(val name: String, var isSelected: Boolean)

@Preview()
@Composable
fun RecyclerViewDefaultPreview() {
    val mNames: ArrayList<Name> = arrayListOf()
    for (i in 0..100) mNames.add(Name("default ${i}", false))
    RecyclerViewTest(mNames)
}