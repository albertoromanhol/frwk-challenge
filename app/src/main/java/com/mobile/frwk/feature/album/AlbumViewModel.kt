package com.mobile.frwk.feature.album

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.data.source.repository.contract.AlbumRepository

class AlbumViewModel @ViewModelInject constructor(
    albumRepository: AlbumRepository
) : ViewModel() {
    val albums: LiveData<List<AlbumEntity>> = albumRepository.getAlbums().map { it }
}
