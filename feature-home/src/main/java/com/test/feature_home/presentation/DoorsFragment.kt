package com.test.feature_home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.test.feature_home.R
import com.test.feature_home.databinding.FragmentCamerasBinding
import com.test.feature_home.databinding.FragmentDoorsBinding
import com.test.feature_home.presentation.adapters.DoorsAdapter
import com.test.feature_home.presentation.adapters.DoorsShortAdapter
import com.test.lib_android_utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoorsFragment : Fragment(R.layout.fragment_doors) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding<FragmentDoorsBinding>()

    private val doorAdapter by lazy{
        DoorsAdapter(LayoutInflater.from(requireContext()))
    }

    private val shortDoorAdapter by lazy{
        DoorsShortAdapter(LayoutInflater.from(requireContext())){
            Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.doorsFromDb()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
    }

    private fun initObservers(){
        viewModel.doorsInfoLD.observe(viewLifecycleOwner){
            shortDoorAdapter.updateList(it)
        }
    }

    private fun initRecycler(){
        binding.rvDoors.adapter = shortDoorAdapter
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            DoorsFragment()
    }
}