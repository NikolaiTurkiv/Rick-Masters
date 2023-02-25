package com.test.feature_home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.test.feature_home.R
import com.test.feature_home.databinding.FragmentCamerasBinding
import com.test.feature_home.presentation.adapters.CameraAdapter
import com.test.lib_android_utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamerasFragment : Fragment(R.layout.fragment_cameras) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding<FragmentCamerasBinding>()

    private val cameraAdapter by lazy {
        CameraAdapter(LayoutInflater.from(requireContext()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.saveCamerasToDb()
        viewModel.camerasFromDb()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeRefresh()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        binding.camerasRv.adapter = cameraAdapter
    }

    private fun initObservers() {
        viewModel.cameraInfoLD.observe(viewLifecycleOwner) {
            cameraAdapter.updateList(it)
        }
    }

    private fun initSwipeRefresh(){

        binding.swipeRefreshCamera.setOnRefreshListener {
            viewModel.camerasFromDb()
        }
        binding.swipeRefreshCamera.isRefreshing = false

    }


    companion object {
        @JvmStatic
        fun newInstance() =
            CamerasFragment()
    }
}