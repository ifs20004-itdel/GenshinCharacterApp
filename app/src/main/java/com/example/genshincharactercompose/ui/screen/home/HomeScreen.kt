package com.example.genshincharactercompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genshincharactercompose.di.Injection
import com.example.genshincharactercompose.model.Hero
import com.example.genshincharactercompose.ui.ViewModelFactory
import com.example.genshincharactercompose.ui.common.UiState
import com.example.genshincharactercompose.ui.components.DataNotFound
import com.example.genshincharactercompose.ui.components.HeroItem
import com.example.genshincharactercompose.ui.components.SearchBar

@Composable
fun HomeScreen (
    modifier: Modifier =Modifier,
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigationToDetail:(String) ->Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllHeroes()
                viewModel.searchHeroes(query)
            }
            is UiState.Success -> {
                HomeContent(
                    heroes = uiState.data,
                    query = query,
                    viewModel::searchHeroes,
                    modifier = modifier,
                    navigationToDetail = navigationToDetail
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun HomeContent(
    heroes : List<Hero>,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigationToDetail: (String) -> Unit
){
    Column(
        modifier = modifier
    ) {
        SearchBar(query = query, onQueryChange = onQueryChange , modifier = modifier.background(Color(0xFF3e9f85)))
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
                    vision = stringResource(id = data.vision),
                    modifier = Modifier.clickable {
                        navigationToDetail(data.name)
                    }
                )
            }
        }
    }
}