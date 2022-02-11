package com.example.noxtton_task.ui.bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.noxtton_task.R
import com.example.noxtton_task.databinding.FragmentBottomBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomFragment : Fragment() {
    private var _binding: FragmentBottomBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bind()
        return root
    }
    private fun bind(){
        val navController = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.navView.setupWithNavController(navController.navController)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}