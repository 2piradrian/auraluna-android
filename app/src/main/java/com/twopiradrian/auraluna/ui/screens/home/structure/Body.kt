package com.twopiradrian.auraluna.ui.screens.home.structure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.ui.components.atoms.TitleLarge
import com.twopiradrian.auraluna.ui.components.molecules.CategoriesList
import com.twopiradrian.auraluna.ui.components.organisms.AudioCoverList
import com.twopiradrian.auraluna.ui.screens.home.viewmodel.HomeViewModel
import com.twopiradrian.auraluna.ui.utils.RouteUtils

@Composable
fun HomeBody(
    audios: List<Audio>,
    selectedCategories: List<AudioCategory>,
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    Column (
        modifier = modifier
    ){
        TitleLarge(
            textId = R.string.home_title
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CategoriesList(
                selected = selectedCategories,
                categories = AudioCategory.toList(),
                onClick = { viewModel.toggleSelectedCategories(it) }
            )
            if (selectedCategories.isEmpty()) {
                AudioCoverList(
                    list = audios,
                    titleId = R.string.home_takeyourtime,
                    onClick = {
                        RouteUtils.audioRoutes[it.type]?.let { route ->
                            navController.navigate("$route/${it.id}") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    }
                )
                AudioCoverList(
                    list = audios,
                    titleId = R.string.home_peacefulmoments,
                    onClick = {
                        RouteUtils.audioRoutes[it.type]?.let { route ->
                            navController.navigate("$route/${it.id}") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    }
                )
                AudioCoverList(
                    list = audios,
                    titleId = R.string.home_createyourritual,
                    onClick = {
                        RouteUtils.audioRoutes[it.type]?.let { route ->
                            navController.navigate("$route/${it.id}") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
            else {
                AudioCoverList(
                    list = audios,
                    titleId = R.string.home_takeyourtime,
                    onClick = {
                        RouteUtils.audioRoutes[it.type]?.let { route ->
                            navController.navigate("$route/${it.id}") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        }
    }
}