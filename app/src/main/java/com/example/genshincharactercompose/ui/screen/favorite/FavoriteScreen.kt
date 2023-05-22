package com.example.genshincharactercompose.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.data.local.entity.HeroEntity
import com.example.genshincharactercompose.di.Injection
import com.example.genshincharactercompose.ui.ViewModelFactory
import com.example.genshincharactercompose.ui.components.DataNotFound
import com.example.genshincharactercompose.ui.components.HeroItem
import com.example.genshincharactercompose.ui.components.TopBar

@Composable
fun FavoriteScreen (
    viewModel: FavoriteScreenViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateBack : () -> Unit,
    navigationToDetail: (String) -> Unit,
) {
    val data by viewModel.getFavoriteHero().observeAsState()
    data?.let { FavoriteContent(heroes = it, navigationToDetail = navigationToDetail, navigateBack = navigateBack) }
}

@Composable
fun FavoriteContent(
    heroes : List<HeroEntity>,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigationToDetail: (String) -> Unit
){
    Column(
        modifier = modifier
    ) {
        TopBar(name = stringResource(id = R.string.favorite) , navigateBack = navigateBack)
        if(heroes.isEmpty()){
            DataNotFound()
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.testTag("HeroList")
        ){
            items(heroes){
                    data ->
                HeroItem(
                    photoUrl = data.photoUrl,
                    name = data.name,
                    rating = data.rating,
                    vision = data.vision,
                    modifier = Modifier.clickable {
                        navigationToDetail(data.name)
                    }
                )
            }
        }
    }
}