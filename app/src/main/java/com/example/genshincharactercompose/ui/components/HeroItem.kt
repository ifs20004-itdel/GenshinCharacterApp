package com.example.genshincharactercompose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme
import com.example.genshincharactercompose.ui.theme.Shapes
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun HeroItem (
    photoUrl: String,
    name: String,
    rating: String,
    vision: Int,
    modifier: Modifier = Modifier,
        ){
    Column(
        modifier = modifier.shadow(2.dp).padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(4.dp)
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = modifier.border(BorderStroke(2.dp, SolidColor(Color.Gray))).fillMaxWidth().background(shape = RectangleShape, color = Color(0xFFa38d56))
        )
        RatingBar(
            value = rating.toFloat(),
            config = RatingBarConfig()
                .size(18.dp)
                .style(RatingBarStyle.HighLighted),
            onValueChange = {},
            onRatingChanged = {}
        )
        Row (
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when(vision){
                R.string.geo_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_geo),
                        contentDescription = stringResource(id = R.string.geo_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.geo_vision),
                        modifier = Modifier
                    )
                }
                R.string.anemo_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_anemo),
                        contentDescription = stringResource(id = R.string.anemo_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.geo_vision),
                        modifier = Modifier
                    )
                }
                R.string.pyro_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_pyro),
                        contentDescription = stringResource(id = R.string.pyro_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.pyro_vision),
                        modifier = Modifier
                    )
                }
                R.string.hydro_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_hydro),
                        contentDescription = stringResource(id = R.string.hydro_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.hydro_vision),
                        modifier = Modifier
                    )
                }
                R.string.dendro_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_dendro),
                        contentDescription = stringResource(id = R.string.dendro_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.dendro_vision),
                        modifier = Modifier
                    )
                }
                R.string.cryo_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_cryo),
                        contentDescription = stringResource(id = R.string.cryo_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.cryo_vision),
                        modifier = Modifier
                    )
                }
                R.string.electro_vision ->{
                    Icon(
                        painter = painterResource(id = R.drawable.element_electro),
                        contentDescription = stringResource(id = R.string.electro_vision),
                        tint = Color.Unspecified)
                    Text(
                        text = stringResource(id = R.string.electro_vision),
                        modifier = Modifier
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroItemPreview(){
    GenshinCharacterComposeTheme {
        HeroItem(
            "https://static.wikia.nocookie.net/gensin-impact/images/2/20/Zhongli_Wish.png/revision/latest/scale-to-width-down/1000?cb=20211226020217",
            "Zhongli",
            "4",
            R.string.geo_vision
        )
    }
}
