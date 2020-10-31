package app.com.access.repository

import app.com.access.api.ApiService
import app.com.access.model.Detail
import app.com.access.model.Item
import io.reactivex.Single

class MyRepository(private val apiService: ApiService) {
     fun getDetail(login:String ): Single<Detail> {
        return apiService.getDetail(login)
    }

     fun getItems(since: Int): Single<List<Item>> {
        return  apiService.getItems(since)
    }
}