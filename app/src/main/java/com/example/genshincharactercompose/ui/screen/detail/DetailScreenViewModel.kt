package com.example.genshincharactercompose.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genshincharactercompose.data.HeroRepository
import com.example.genshincharactercompose.data.local.entity.HeroEntity
import com.example.genshincharactercompose.model.DetailHero
import com.example.genshincharactercompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val repository: HeroRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailHero>> =
        MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<DetailHero>>
        get() = _uiState

//    val favoriteStatus =

    fun getDetailHero(heroName: String){
        viewModelScope.launch {
            _uiState.value  = UiState.Loading
            _uiState.value = UiState.Success(repository.getDetailHero(heroName))
        }
    }

    fun saveHero(hero: HeroEntity) = repository.saveHero(hero)

    fun deleteHero(heroName:String) = repository.deleteHero(heroName)

    fun isFavoriteHero(heroName: String): LiveData<Boolean> = repository.isFavoriteHero(heroName)
}