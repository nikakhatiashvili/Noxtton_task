package com.example.noxtton_task.ui.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noxtton_task.R
import com.example.noxtton_task.databinding.FragmentDetailBinding
import com.example.noxtton_task.databinding.UserFragmentBinding
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.ui.detail.DetailFragmentArgs
import com.example.noxtton_task.ui.detail.DetailViewModel
import com.example.noxtton_task.ui.home.RepositoryAdapter
import com.example.noxtton_test.ui.home.HomeFragmentDirections

class UserFragment : Fragment() {

    private val args: UserFragmentArgs by navArgs()
    private lateinit var item: Item
    private lateinit var name: String
    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var adapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bind()
        return root
    }
    private fun bind(){
        name = args.argItem
        adapter = RepositoryAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        userViewModel.getUserRepo(name)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            userViewModel.repositories.observe(viewLifecycleOwner){
                adapter.data = it
            }
        }
        adapter.onItemClick = {
            val direction = UserFragmentDirections.actionUserFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }
    }

}