package com.example.genshincharactercompose.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.data.local.entity.HeroEntity
import com.example.genshincharactercompose.di.Injection
import com.example.genshincharactercompose.model.FakeHeroDataSource
import com.example.genshincharactercompose.model.Hero
import com.example.genshincharactercompose.ui.ViewModelFactory
import com.example.genshincharactercompose.ui.common.UiState
import com.example.genshincharactercompose.ui.components.TopBar
import com.example.genshincharactercompose.ui.components.attribute.Attribute
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme
import com.example.genshincharactercompose.ui.theme.Shapes
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun DetailScreen (
    heroName : String,
    viewModel: DetailScreenViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    navigateBack : () -> Unit,
) {
    val favoriteStatus by viewModel.isFavoriteHero(heroName).observeAsState()
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        uiState ->
        when(uiState){
            is UiState.Loading ->{
                viewModel.getDetailHero(heroName)
            }
            is UiState.Success ->{
                val data = uiState.data
                val hero = data.hero
                val saveHero = HeroEntity(hero.name, hero.photoUrl, hero.rating,  stringResource(id = hero.vision))
                favoriteStatus?.let {
                    DetailContent(
                        name = data.name,
                        hero = data.hero,
                        weapon = data.weapon,
                        constellation = data.constellation,
                        region = data.region,
                        description = data.description,
                        onBackClick = navigateBack,
                        isFavoriteHero = it,
                        saveHero = {
                            if(it){
                                viewModel.deleteHero(data.name)
                            }else{
                                viewModel.saveHero(saveHero)
                            }
                        },
                    )
                }
            }
            is UiState.Error ->{
            }
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    hero : Hero,
    weapon: Int,
    constellation : String,
    region : Int,
    description: String,
    isFavoriteHero: Boolean,
    saveHero: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Column(
            modifier = modifier
        ) {
            TopBar(
                name = name,
                navigateBack = onBackClick
            )
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                Column (
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                    ) {
                        AsyncImage(
                            model = hero.photoUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .height(300.dp)
                                .clip(Shapes.small)
                        )
                        RatingBar(
                            modifier = modifier
                                .align(
                                    Alignment.BottomStart
                                )
                                .padding(14.dp),
                            value = hero.rating.toFloat(),
                            config = RatingBarConfig()
                                .size(24.dp)
                                .style(RatingBarStyle.HighLighted),
                            onValueChange = {},
                            onRatingChanged = {}
                        )
                    }
                    Text(
                        text = constellation,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .background(
                                Color(0xFFa38d56),
                                shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
                            )
                            .fillMaxWidth()
                            .padding(2.dp)
                    )
                }
                Attribute(
                    weapon = stringResource(id = weapon),
                    region = stringResource(id = region),
                    vision = stringResource(id = hero.vision)
                )

                Text(
                    text = description,
                    style =  MaterialTheme.typography.body2,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Justify,
                    modifier = modifier
                        .padding(24.dp),
                )
            }
        }
        FloatingActionButton(
            onClick = { saveHero() },
            backgroundColor = Color(0xFF3e9f85),
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
                .testTag("FavoriteButton")
        ) {
            if(isFavoriteHero){
                Icon(painter = painterResource(
                    id = R.drawable.baseline_favorite_full_24),
                    contentDescription = stringResource(id = R.string.favorite),
                    modifier.testTag("FavoriteFull")
                )
            }else{
                Icon(painter = painterResource(
                    id = R.drawable.baseline_favorite_border_24),
                    contentDescription = stringResource(id = R.string.favorite),
                    modifier.testTag("favoriteEmpty")
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview(){
    GenshinCharacterComposeTheme {
        DetailContent(
            name = "Zhongli",
            hero = FakeHeroDataSource.dummyHeroes[55],
            weapon = R.string.sword,
            constellation = "Lapis Dei",
            region = R.string.liyue,
            onBackClick = {},
            saveHero = {},
            description = " This is dummy data ",
            isFavoriteHero = true
        )
    }
}
