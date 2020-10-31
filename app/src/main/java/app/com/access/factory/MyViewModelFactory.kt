package app.com.access.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.com.access.api.ApiService
import app.com.access.repository.MyRepository
import app.com.access.viewmodel.DetailViewModel
import app.com.access.viewmodel.MainViewModal

class MyViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModal::class.java)) {
            return MainViewModal(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}