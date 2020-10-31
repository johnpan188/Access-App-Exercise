package app.com.access.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import app.com.access.api.ApiService
import app.com.access.model.Detail
import app.com.access.model.Item
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyRepository(private val apiService: ApiService) {
     fun getDetail(login:String ): Single<Detail> {
        return apiService.getDetail(login)
    }

     fun getItems(): Observable<PagedList<Item>> {
         val dataSource = ItemPagingDataSourceFactory(apiService)
         val pagedListConfig = PagedList.Config.Builder()
             .setPageSize(20)
             .setPrefetchDistance(4)
             .setMaxSize(60)
             .setEnablePlaceholders(true)
             .build()
         return RxPagedListBuilder(dataSource, pagedListConfig)
             .setFetchScheduler(Schedulers.io())
             .setNotifyScheduler(AndroidSchedulers.mainThread())
             .buildObservable()
    }
}