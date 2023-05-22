package com.example.genshincharactercompose.ui.components.attribute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun Weapon (
    weapon: Int,
    description: Int,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            ) {
        Icon(
            painter = painterResource(id = weapon),
            contentDescription = stringResource(id = description),
            tint = Color.Unspecified,
            modifier = modifier.size(24.dp)
        )
        Text(
            text = stringResource(id = description),
            fontFamily = FontFamily.Serif,
            modifier = modifier
        )
    }
}
