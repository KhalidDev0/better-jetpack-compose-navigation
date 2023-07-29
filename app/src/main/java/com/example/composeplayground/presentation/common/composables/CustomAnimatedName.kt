package com.example.composeplayground.presentation.common.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomAnimatedName(
    name: String,
    expanded: Boolean,
){
    Text(text = name.first().toString(), style = TextStyle(fontSize = 80.sp, color = Color.Black))

    AnimatedVisibility(
        visible = expanded
    ) {
        Text(
            text = name.substring(1),
            style = TextStyle(fontSize = 40.sp, color = Color.Black),
        )
    }
}