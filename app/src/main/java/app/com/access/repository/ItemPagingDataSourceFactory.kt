package app.com.access.repository

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.com.access.api.ApiService
import app.com.access.model.Item

class ItemPagingDataSourceFactory(private val apiService: ApiService) : DataSource.Factory<Int, Item>() {
    override fun create(): DataSource<Int, Item> {
        return ItemPagingDataSource(apiService)
    }
}

class ItemPagingDataSource(val apiService: ApiService): PageKeyedDataSource<Int, Item>() {
    var count = 0
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        count = 0
        var result = apiService.getItems(0).blockingGet()
        count += result.size
        callback.onResult(result, null, result[result.lastIndex].id)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        if (count < 100) {
            var result = apiService.getItems(params.key).blockingGet()
            count += result.size
            callback.onResult(result,  result[result.lastIndex].id)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
    }

}