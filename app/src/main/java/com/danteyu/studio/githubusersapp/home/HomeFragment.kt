package com.danteyu.studio.githubusersapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.danteyu.studio.githubusersapp.databinding.FragmentHomeBinding
import com.danteyu.studio.githubusersapp.ext.getVmFactory
import com.danteyu.studio.githubusersapp.network.LoadApiStatus

/**
 * Created by George Yu on 2020/4/26.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerHome.adapter = HomeAdapter(HomeAdapter.OnClickListener {})

        binding.layoutSwipeRefreshHome.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it != LoadApiStatus.LOADING)
                    binding.layoutSwipeRefreshHome.isRefreshing = false
            }
        })

        return binding.root
    }
}