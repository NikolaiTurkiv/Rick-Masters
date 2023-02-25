package com.test.feature_home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.test.feature_home.R
import com.test.feature_home.databinding.FragmentDoorsBinding
import com.test.feature_home.presentation.adapters.DoorsAdapter
import com.test.lib_android_utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoorsFragment : Fragment(R.layout.fragment_doors) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding<FragmentDoorsBinding>()

    private val doorAdapter by lazy {
        DoorsAdapter(LayoutInflater.from(requireContext()), {
            EditDoorItemFragment.newInstance(it).apply {
                show(this@DoorsFragment.parentFragmentManager, EditDoorItemFragment.TAG)
            }
        }, {
            viewModel.updateDoorList(it)
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.saveDoors()
        viewModel.doorsFromDb()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
    }

    private fun initObservers() {
        viewModel.doorsInfoLD.observe(viewLifecycleOwner) {
            doorAdapter.updateList(it)
        }
    }

    private fun initRecycler() {
        binding.rvDoors.adapter = doorAdapter
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            DoorsFragment()
    }
}