package com.example.noxtton_task.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.noxtton_task.R
import com.example.noxtton_task.databinding.FragmentDetailBinding

import com.example.noxtton_task.model.Item
import com.example.noxtton_test.ui.dashboard.DashboardFragmentDirections
import com.example.noxtton_test.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KProperty

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val args:DetailFragmentArgs by navArgs()
    private lateinit var item:Item
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bind()
        return root
    }
    @SuppressLint("SetTextI18n")
    private fun bind(){
        item = args.argItem
//        d("casdasdas",item.full_name.toString())
        binding.appCompatButton.setOnClickListener {
           val link =  Uri.parse(item.html_url)
            startActivity(Intent(Intent.ACTION_VIEW,link))
        }
        with(binding){
            with(item){
                githFullName.text = full_name
                githDescription.text = description
                githStarCount.text = stargazers_count.toString() + " Watchers"
                githPublic.text = open_issues_count.toString() + " Issues"
                githForks.text = forks.toString() + " Forks"
                githMainBranch.text = default_branch
                githMainLang.text = language
                githCreate.text = "created at " + created_at?.dropLast(10)
                githUpdate.text = "updated at " +  updated_at?.dropLast(10)
                githName.text = owner?.login
                Glide.with(context!!).load(item.owner?.avatar_url).into(avatar)
            }
        }
        detailViewModel.exists(item.id!!)
        binding.saveImg.setOnClickListener {
            detailViewModel.addItem(item)
            binding.saveImg.visibility = View.GONE
            binding.deleteImg.visibility = View.VISIBLE
        }
        binding.deleteImg.setOnClickListener {
            detailViewModel.delete(item.id!!)
            binding.saveImg.visibility = View.VISIBLE
            binding.deleteImg.visibility = View.GONE
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.exisist.observe(viewLifecycleOwner){
                if (it){
                    binding.saveImg.visibility = View.GONE
                    binding.deleteImg.visibility = View.VISIBLE
                }else{
                    binding.saveImg.visibility = View.VISIBLE
                    binding.deleteImg.visibility = View.GONE
                }
            }
        }
        binding.Goback.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_navigation_home)
        }
        binding.githRepo.setOnClickListener {
            val direction = DetailFragmentDirections.actionDetailFragmentToUserFragment(item.owner?.login!!)
            findNavController().navigate(direction)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}



