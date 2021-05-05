package com.mobile.frwk.feature.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.databinding.PostFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels()

    private var postAdapter: PostAdapter? = null

    private var _binding: PostFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PostFragmentBinding.inflate(inflater, container, false)
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
        postAdapter = PostAdapter()
        binding.postList.adapter = postAdapter
    }

    private fun subscribeUi() {
        viewModel.posts.observe(
            viewLifecycleOwner,
            {
                populateAdapter(it)
            }
        )
    }

    private fun populateAdapter(it: List<PostEntity>?) {
        postAdapter?.submitList(it)
    }
}
