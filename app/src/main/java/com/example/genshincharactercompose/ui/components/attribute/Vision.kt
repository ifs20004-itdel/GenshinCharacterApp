package com.example.genshincharactercompose.ui.components.attribute

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily

@Composable
fun Vision(
    vision: Int,
    description: Int,
    modifier: Modifier = Modifier
){
    Row{
        Icon(
            painter = painterResource(id = vision),
            contentDescription = stringResource(id = description),
            tint = Color.Unspecified)
        Text(
            text = stringResource(id = description),
            fontFamily = FontFamily.Serif,
            modifier = modifier
        )
    }
}