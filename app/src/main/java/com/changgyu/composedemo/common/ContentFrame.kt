package com.changgyu.composedemo.common

import androidx.compose.runtime.Composable

@Composable
fun Header(content: @Composable () -> Unit){
    content()
}

@Composable
fun Body(content: @Composable () -> Unit){
    content()
}

@Composable
fun Footer(content: @Composable () -> Unit){
    content()
}
