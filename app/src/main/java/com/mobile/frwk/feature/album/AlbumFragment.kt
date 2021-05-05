package com.mobile.frwk.feature.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.databinding.AlbumFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private val viewModel: AlbumViewModel by viewModels()

    private var albumAdapter: AlbumAdapter? = null

    private var _binding: AlbumFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        albumAdapter = AlbumAdapter()
        binding.albumList.adapter = albumAdapter
    }

    private fun subscribeUi() {
        viewModel.albums.observe(
            viewLifecycleOwner,
            {
                populateAdapter(it)
            }
        )
    }

    private fun populateAdapter(it: List<AlbumEntity>?) {
        albumAdapter?.submitList(it)
    }
}
