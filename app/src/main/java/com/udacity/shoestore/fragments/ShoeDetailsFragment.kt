package com.udacity.shoestore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: ShoeDetailsFragmentBinding

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_details_fragment, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner=viewLifecycleOwner
        viewModel.emptyData()

        saveClickListeners()
        cancelClickListeners()

        return binding.root
    }

    private fun saveClickListeners() {
        binding.saveButton.setOnClickListener {
            Log.i("FINDME","Save Button")
            viewModel.addNewShoe()
            findNavController().navigateUp()
        }
    }

    private fun cancelClickListeners() {
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}