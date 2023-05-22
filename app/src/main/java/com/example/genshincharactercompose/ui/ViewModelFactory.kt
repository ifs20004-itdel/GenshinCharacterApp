package com.example.genshincharactercompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genshincharactercompose.data.HeroRepository
import com.example.genshincharactercompose.ui.screen.detail.DetailScreenViewModel
import com.example.genshincharactercompose.ui.screen.favorite.FavoriteScreenViewModel
import com.example.genshincharactercompose.ui.screen.home.HomeScreenViewModel

class ViewModelFactory (private val repository: HeroRepository):
    ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            return HomeScreenViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailScreenViewModel::class.java)){
            return DetailScreenViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(FavoriteScreenViewModel::class.java)){
            return FavoriteScreenViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}