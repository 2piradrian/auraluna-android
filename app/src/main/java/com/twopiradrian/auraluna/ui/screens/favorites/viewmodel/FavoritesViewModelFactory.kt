package com.twopiradrian.auraluna.ui.screens.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.twopiradrian.auraluna.infrastructure.repositories.AudioRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoriteRepository

class FavoritesViewModelFactory(
    private val favoritesRepository: FavoriteRepository,
    private val audioRepository: AudioRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(
                favoritesRepository = favoritesRepository,
                audioRepository = audioRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
