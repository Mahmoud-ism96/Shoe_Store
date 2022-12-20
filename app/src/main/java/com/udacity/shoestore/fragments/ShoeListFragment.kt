package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeListViewModel


class ShoeListFragment : Fragment() {

    private lateinit var binding: ShoeListFragmentBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_list_fragment, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.shoes.observe(this.viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                for (shoe in it) {
                    addShoe(shoe)
                }
            }
        }

        editOnClickListener()
        setHasOptionsMenu(true)


        return binding.root
    }

    private fun editOnClickListener() {
        binding.editShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun addShoe(newShoe: Shoe) {
        val itemBinding = ShoeItemBinding.inflate(LayoutInflater.from(context))

        itemBinding.shoeInfoName.text = newShoe.name
        itemBinding.shoeInfoSize.text = newShoe.size.toString()
        itemBinding.shoeInfoCompany.text = newShoe.company
        itemBinding.shoeInfoDescription.text = newShoe.description

        binding.shoeContainer.addView(itemBinding.root)
    }
}