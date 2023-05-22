package com.example.genshincharactercompose.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.genshincharactercompose.data.HeroRepository

class FavoriteScreenViewModel (
    private val repository: HeroRepository
        ): ViewModel() {
        fun getFavoriteHero() = repository.getFavoriteHero()
}
