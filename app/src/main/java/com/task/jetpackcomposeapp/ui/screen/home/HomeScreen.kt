package com.task.jetpackcomposeapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.jetpackcomposeapp.data.room.PetShopEntity
import com.task.jetpackcomposeapp.ui.components.*
import com.task.jetpackcomposeapp.utils.UiState

@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.allData.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                HomeContent(
                    listPetShop = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavorite = homeViewModel::updateFavorite
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listPetShop: List<PetShopEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listPetShop.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(listPetShop, navController, scaffoldState, onUpdateFavorite)
        }
    }
}

