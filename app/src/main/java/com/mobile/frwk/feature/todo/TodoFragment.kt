package com.mobile.frwk.feature.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mobile.frwk.data.local.database.model.TodoEntity
import com.mobile.frwk.databinding.TodoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : Fragment() {

    private val viewModel: TodoViewModel by viewModels()

    private var todoAdapter: TodoAdapter? = null

    private var _binding: TodoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        todoAdapter = TodoAdapter()
        binding.todoList.adapter = todoAdapter
    }

    private fun subscribeUi() {
        viewModel.todos.observe(
            viewLifecycleOwner,
            {
                populateAdapter(it)
            }
        )
    }

    private fun populateAdapter(it: List<TodoEntity>?) {
        todoAdapter?.submitList(it)
    }
}
