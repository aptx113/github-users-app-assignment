package com.danteyu.studio.githubusersapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.danteyu.studio.githubusersapp.databinding.FragmentDetailBinding
import com.danteyu.studio.githubusersapp.ext.getVmFactory

/**
 * Created by George Yu on 2020/4/28.
 */
class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel> {
        getVmFactory(
            DetailFragmentArgs.fromBundle(
                requireArguments()
            ).gitHubUserKey
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.backToHome.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigateUp()
                viewModel.onHomeBacked()
            }
        })

        return binding.root
    }
}