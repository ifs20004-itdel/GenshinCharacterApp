package com.example.genshincharactercompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genshincharactercompose.data.HeroRepository
import com.example.genshincharactercompose.model.Hero
import com.example.genshincharactercompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel (
    private val repository: HeroRepository
        ): ViewModel() {

    private val _uiState : MutableStateFlow<UiState<List<Hero>>> =
        MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<Hero>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllHeroes(){
          viewModelScope.launch {
              repository.getHero()
                  .catch {
                      _uiState.value = UiState.Error(it.message.toString())
                  }
                  .collect{
                      hero ->
                      _uiState.value = UiState.Success(hero)
                  }
          }
      }

    fun searchHeroes(newQuery: String){
        viewModelScope.launch {
        _query.value =  newQuery
        repository.searchHero(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect{
                hero ->
                _uiState.value = UiState.Success(hero)
            }
        }
    }
}