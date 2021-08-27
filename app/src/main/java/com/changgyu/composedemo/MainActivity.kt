package com.changgyu.composedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.changgyu.composedemo.bottom_navigation.BottomNavigationActivity
import com.changgyu.composedemo.demo_project.DemoProjectActivity
import com.changgyu.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainScreen()
            }
        }
    }

}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeDemoTheme {
        content()
    }

}

@Composable
fun MainScreen() {
    val context: Context = LocalContext.current
    var intent: Intent

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                intent = Intent(context, RecyclerViewActivity::class.java)
                startActivity(context, intent, null)
            }) {
            Text(text = "RecyclerView Test", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            intent = Intent(context, BottomNavigationActivity::class.java)
            startActivity(context, intent, null)
        }) {
            Text(text = "BottomNavigation Test", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            intent = Intent(context, DemoProjectActivity::class.java)
            startActivity(context, intent, null)
        }) {
            Text(text = "DemoProject Test", fontSize = 20.sp)
        }
    }

}