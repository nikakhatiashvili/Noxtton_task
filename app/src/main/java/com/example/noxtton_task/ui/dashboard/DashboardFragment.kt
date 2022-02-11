package com.example.noxtton_test.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noxtton_task.databinding.FragmentDashboardBinding
import com.example.noxtton_task.ui.detail.DetailViewModel
import com.example.noxtton_task.ui.home.RepositoryAdapter
import com.example.noxtton_test.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val detailViewModel: DetailViewModel by activityViewModels()
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var adapter: RepositoryAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bind()
        return root
    }

    private fun bind(){
        adapter = RepositoryAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            detailViewModel.readData.observe(viewLifecycleOwner){
                if(!it.isNullOrEmpty()){
                    adapter.data =  it
                }else{

                }
            }
        }
        adapter.onItemClick  = {
            val direction = DashboardFragmentDirections.actionNavigationDashboardToDetailFragment(it)
            findNavController().navigate(direction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}