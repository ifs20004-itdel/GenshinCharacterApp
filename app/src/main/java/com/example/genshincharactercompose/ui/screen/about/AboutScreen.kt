package com.example.genshincharactercompose.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.ui.components.TopBar
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme

@Composable
fun AboutScreen (
    modifier: Modifier = Modifier,
    navigateBack : () -> Unit,
    ) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopBar(name = stringResource(id = R.string.profile), navigateBack)
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_img),
                contentDescription = null,
                modifier = modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
                    .padding(8.dp)
                )
            Text(
                text = stringResource(id = R.string.username),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = modifier.padding(8.dp)
            )
            Text(
                text = stringResource(id = R.string.email),
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontSize = 11.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    GenshinCharacterComposeTheme {
        AboutScreen(navigateBack = {})
    }
}
