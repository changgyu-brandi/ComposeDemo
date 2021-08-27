package com.changgyu.composedemo.demo_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.changgyu.composedemo.MyApp
import com.changgyu.composedemo.R
import com.changgyu.composedemo.demo_project.bookmark.BookmarkScreen
import com.changgyu.composedemo.demo_project.category.CategoryScreen
import com.changgyu.composedemo.demo_project.home.HomeScreen
import com.changgyu.composedemo.demo_project.mypage.MypageScreen
import com.google.accompanist.pager.ExperimentalPagerApi

class DemoProjectActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                BottomNavigationTest()
            }
        }
    }

}


@Composable
fun BottomNavigationTest() {
    val navController = rememberNavController()
    Scaffold(
        //topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController = navController)
    }

}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(com.changgyu.composedemo.R.string.app_name),
                fontSize = 18.sp
            )
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Category.route) {
            CategoryScreen()
        }
        composable(NavigationItem.Bookmark.route) {
            BookmarkScreen()
        }
        composable(NavigationItem.Mypage.route) {
            MypageScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = NavigationItem.values()

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // 백스택에 startDestinationRoute 만 추가 되게 함
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // 스택에 중복돼서 올라가지 않게 하기위해
                        launchSingleTop = true
                        // 이전에 선택된 화면상태를 복구할때
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.icon_home, "Home")
    object Category : NavigationItem("category", R.drawable.icon_category, "Category")
    object Bookmark : NavigationItem("bookmark", R.drawable.icon_bookmark, "Bookmark")
    object Mypage : NavigationItem("mypage", R.drawable.icon_mypage, "Mypage")

    companion object{
        fun values() = listOf(Home, Category, Bookmark, Mypage)
    }
}