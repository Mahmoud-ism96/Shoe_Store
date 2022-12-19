package com.udacity.shoestore.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val shoeList = mutableListOf<Shoe>()

    var shoeName: String = ""
    var shoeSize: String = ""
    var shoeCompany: String = ""
    var shoeDescription: String = ""

    fun addNewShoe() {
        if (ifEmpty()) {
            Log.i("FINDME","Not Empty")
            shoeList.add(Shoe(shoeName, shoeSize.toDouble(), shoeCompany, shoeDescription))
            _shoes.value = shoeList
        }

    }

    private fun ifEmpty(): Boolean {
        return !(shoeName.isBlank() || shoeSize.isBlank() || shoeCompany.isBlank() || shoeDescription.isBlank())
    }

    fun emptyData() {
        shoeName = ""
        shoeSize = ""
        shoeCompany = ""
        shoeDescription = ""
    }


}
