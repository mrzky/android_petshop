package com.task.jetpackcomposeapp.ui.screen.detail

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
class DetailViewModel @Inject constructor(private val repository: PetShopRepository) : ViewModel() {
    private val _data = MutableStateFlow<UiState<PetShopEntity>>(UiState.Loading)
    val data = _data.asStateFlow()

    fun getData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData(id)
                .catch { _data.value = UiState.Error(it.message.toString()) }
                .collect { _data.value = UiState.Success(it) }
        }
    }

    fun editFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editFavorite(id, isFavorite)
        }
    }
}