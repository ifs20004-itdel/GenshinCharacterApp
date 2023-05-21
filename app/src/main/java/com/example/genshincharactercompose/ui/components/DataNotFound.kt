package com.example.genshincharactercompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme

@Composable
fun DataNotFound (
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize().alpha(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_data),
            contentDescription = null,
            )
        Text(
            text = stringResource(id = R.string.empty_data),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DataNotFoundPreview(){
    GenshinCharacterComposeTheme {
        DataNotFound()
    }
}