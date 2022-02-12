package com.example.noxtton_test.ui.home

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noxtton_task.databinding.FragmentHomeBinding

import com.example.noxtton_task.ui.home.RepositoryAdapter
import com.example.noxtton_task.ui.home.SearchAdapter
import com.example.noxtton_task.util.Resource
import com.example.noxtton_test.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!
    private  var exists:Boolean? = null
    private lateinit var adapter: RepositoryAdapter
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bind()
        return root
    }

    private fun bind() {
        adapter = RepositoryAdapter()
        searchAdapter = SearchAdapter()

        with(binding) {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            search.doAfterTextChanged {
                viewLifecycleOwner.lifecycleScope.launch {
                    val text = search.text.toString()
                    if (checkText(text)) {
                        homeViewModel.search(text)
                    } else {
                        adapter.data = emptyList()
                    }
                }
            }
            searchRecyclerview.adapter = searchAdapter
            searchRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.search.observe(viewLifecycleOwner){
                if (it.isNotEmpty()){
                    searchAdapter.data = it.asReversed()
                }
            }
        }
        searchAdapter.onItemClick = {
            binding.search.setText(it)
        }
        adapter.onItemClick = {
            d("sadasdfa",it.full_name.toString())
            val direction = HomeFragmentDirections.actionNavigationHomeToDetailFragment(it)
            findNavController().navigate(direction)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.repositories.observe(viewLifecycleOwner) {
                if (!binding.search.text?.isEmpty()!!){
                    adapter.data = it
                }

            }
        }
    }




    private fun checkText(str: String) = !str.isNullOrEmpty()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}