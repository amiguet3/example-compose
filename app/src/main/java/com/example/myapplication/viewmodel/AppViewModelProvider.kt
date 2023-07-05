package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.data.AppContainer
import com.example.myapplication.data.AppDataContainer

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            val app = this[AndroidViewModelFactory.APPLICATION_KEY]!!
            println("app ${app::class.simpleName}")
            PlanetViewModel(
                application().container.itemsRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.application(): PlanetsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PlanetsApplication)

class PlanetsApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}