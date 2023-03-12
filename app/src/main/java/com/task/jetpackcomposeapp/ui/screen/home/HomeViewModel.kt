package com.task.jetpackcomposeapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.jetpackcomposeapp.data.room.PetShopEntity
import com.task.jetpackcomposeapp.data.repository.PetShopRepository
import com.task.jetpackcomposeapp.utils.PetShopData
import com.task.jetpackcomposeapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PetShopRepository) : ViewModel() {
    private val _allData = MutableStateFlow<UiState<List<PetShopEntity>>>(UiState.Loading)
    val allData = _allData.asStateFlow()

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collect { petShop ->
                when (petShop.isEmpty()) {
                    true -> repository.addAll(PetShopData.dummy)
                    else -> _allData.value = UiState.Success(petShop)
                }
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.search(query)
                .catch { _allData.value = UiState.Error(it.message.toString()) }
                .collect { _allData.value = UiState.Success(it) }
        }
    }

    fun updateFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editFavorite(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
        search(query)
    }
}