package com.task.jetpackcomposeapp.ui.screen.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.jetpackcomposeapp.data.room.PetShopEntity
import com.task.jetpackcomposeapp.ui.components.*
import com.task.jetpackcomposeapp.utils.UiState

@Composable
fun FavoriteScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavorite.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                FavoriteContent(
                    listFavorite = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavorite = favoriteViewModel::editFavorite
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavorite: List<PetShopEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavorite.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableContent(listFavorite, navController, scaffoldState, onUpdateFavorite)
    }
}