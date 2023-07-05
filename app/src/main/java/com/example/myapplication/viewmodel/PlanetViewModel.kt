package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Item
import com.example.myapplication.data.ItemsRepository
import com.example.myapplication.repositories.PlanetRepository
import com.example.myapplication.service.Planet
import kotlinx.coroutines.launch

class PlanetViewModel(private val itemsRepository: ItemsRepository): ViewModel() {
    private val repository = PlanetRepository()


    private val _planets = MutableLiveData<List<Planet>>()
    val planets: LiveData<List<Planet>> = _planets

    fun fetchPlanets() {
        viewModelScope.launch {
            val localPlanets =
            try {
                val planets = repository.getPlanets()
                repository.postData()
                _planets.value = planets
            } catch (e: Exception) {
                println("error on fetchPlanets(): $e")
            }
        }
    }

    suspend fun createItem() {
            itemsRepository.insertItem(Item(0, "Pilota", 1))
    }
}



//data class ItemUiState(
//    val itemDetails: ItemDetails = ItemDetails(),
//    val isEntryValid: Boolean = false
//)
//
//data class ItemDetails(
//    val id: Int = 0,
//    val name: String = "",
//    val price: String = "",
//    val quantity: String = "",
//)
//
//fun ItemDetails.toItem(): Item = Item(
//    id = id,
//    name = name,
//    quantity = quantity.toIntOrNull() ?: 0
//)
//
//fun Item.toItemDetails(): ItemDetails = ItemDetails(
//    id = id,
//    name = name,
//    quantity = quantity.toString()
//)
//
//fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
//    itemDetails = this.toItemDetails(),
//    isEntryValid = isEntryValid
//)