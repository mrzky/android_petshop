package com.task.jetpackcomposeapp.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.jetpackcomposeapp.data.room.PetShopEntity
import com.task.jetpackcomposeapp.data.repository.PetShopRepository
import com.task.jetpackcomposeapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: PetShopRepository) : ViewModel() {
    private val _allFavorite = MutableStateFlow<UiState<List<PetShopEntity>>>(UiState.Loading)
    val allFavorite = _allFavorite.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavorite()
                .catch { _allFavorite.value = UiState.Error(it.message.toString()) }
                .collect { _allFavorite.value = UiState.Success(it) }
        }
    }

    fun editFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editFavorite(id, isFavorite)
        }
    }
}