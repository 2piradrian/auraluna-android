package com.twopiradrian.auraluna.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.twopiradrian.auraluna.core.navigation.AppScreens
import com.twopiradrian.auraluna.domain.entities.AudioType
import com.twopiradrian.auraluna.ui.components.organisms.AppNavigationBar
import com.twopiradrian.auraluna.ui.layouts.AppLayout
import com.twopiradrian.auraluna.ui.screens.favorites.structure.FavoritesBody
import com.twopiradrian.auraluna.ui.screens.favorites.viewmodel.FavoritesViewModel
import com.twopiradrian.auraluna.ui.utils.ScreenUtils
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    navController: NavController,
    screenUtils: ScreenUtils,
    viewModel: FavoritesViewModel
) {

    val favorites by viewModel.favorites.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getAudios()
    }

    AppLayout(
        content = {
            FavoritesBody(
                favorites = favorites,
                toggleFavorite = {
                    viewModel.toggleFavorite(it)
                },
                onFavoriteClick = { favorite ->
                    scope.launch {
                        val audio = viewModel.getAudio(favorite.audioId)
                        if (audio != null) {
                            when (audio.type) {
                                AudioType.LINEAL -> {
                                    navController.navigate(AppScreens.LinealPlayerScreen.route + "/${audio.id}")
                                }
                                AudioType.LOOP -> {
                                    navController.navigate(AppScreens.LoopPlayerScreen.route + "/${audio.id}")
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        },
        bottomBar = {
            AppNavigationBar(
                navController = navController,
            )
        }
    )
}
