package app.com.access.api

import app.com.access.model.Detail
import app.com.access.model.Item
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {
    override fun getDetail(login:String ): Single<Detail> {
        return Rx2AndroidNetworking.get("https://api.github.com/users/${login}")
            .build()
            .getObjectSingle(Detail::class.java)
    }

    override fun getItems(since: Int): Single<List<Item>> {
        return Rx2AndroidNetworking.get("https://api.github.com/users?per_page=100&since=${since}")
            .build()
            .getObjectListSingle(Item::class.java)
    }


}