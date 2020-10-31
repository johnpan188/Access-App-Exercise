package app.com.access.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.com.access.api.ApiService
import app.com.access.model.Detail
import app.com.access.repository.MyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: MyRepository) : ViewModel() {

    private val detail = MutableLiveData<Detail>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchDetail(login: String) {
        compositeDisposable.add(
            repository.getDetail(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ detailData ->
                    detail.postValue(detailData)
                }, { throwable ->
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getDetail(): LiveData<Detail> {
        return detail
    }

}