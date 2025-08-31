package com.twopiradrian.auraluna.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.infrastructure.repositories.AudioRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val favoritesRepository: FavoriteRepository,
    private val audiosRepository: AudioRepository
) : ViewModel() {

    private val _audios = MutableStateFlow(emptyList<Audio>())
    val audios: StateFlow<List<Audio>> = _audios

    private val _selectedCategories = MutableStateFlow(emptyList<AudioCategory>())
    val selectedCategories: StateFlow<List<AudioCategory>> = _selectedCategories

    fun getAudios() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (_selectedCategories.value.isEmpty()) {
                    val audiosResult: List<Audio> = audiosRepository.getAll()
                    _audios.value = audiosResult
                } else {
                    val audiosResult: List<Audio> =
                        audiosRepository.getByCategories(_selectedCategories.value)
                    _audios.value = audiosResult
                }
            }
        }
    }

    fun toggleSelectedCategories(category: AudioCategory) {
        if (_selectedCategories.value.contains(category)) {
            _selectedCategories.value = _selectedCategories.value.filter { it != category }
        }
        else {
            _selectedCategories.value += category

        }
    }

}