package app.com.access.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.com.access.api.ApiService
import app.com.access.model.Item
import app.com.access.repository.MyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModal(private val repository: MyRepository) : ViewModel() {

    private val items = MutableLiveData<List<Item>>()
    private val compositeDisposable = CompositeDisposable()
    private var since = 0

    fun fetchItems() {
        compositeDisposable.add(
            repository.getItems(since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ itemsData ->
                    since = itemsData[itemsData.lastIndex].id
                    items.postValue(itemsData)
                }, { throwable ->
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getItems(): LiveData<List<Item>> {
        return items
    }

}