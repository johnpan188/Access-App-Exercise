package app.com.access.api

import app.com.access.model.Detail
import app.com.access.model.Item
import io.reactivex.Single

interface ApiService {

    fun getDetail(login:String): Single<Detail>

    fun getItems(page: Int): Single<List<Item>>

}