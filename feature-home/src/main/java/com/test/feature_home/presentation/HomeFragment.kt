package com.test.feature_home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.test.feature_home.R
import com.test.feature_home.databinding.FragmentHomeBinding
import com.test.feature_home.presentation.adapters.PagerAdapter
import com.test.lib_android_utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding<FragmentHomeBinding>()
    private val adapter by lazy {
        PagerAdapter(requireActivity())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductDetailsViewPager()
    }


    private fun initProductDetailsViewPager() {
        binding.productDetailsViewPager.adapter = adapter
        TabLayoutMediator(binding.homeTab, binding.productDetailsViewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = getString(com.test.core_ui.R.string.cameras)
                else -> tab.text = getString(com.test.core_ui.R.string.doors)
            }
        }.attach()

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

}