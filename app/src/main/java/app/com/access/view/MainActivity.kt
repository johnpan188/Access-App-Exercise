package app.com.access.view

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.com.access.api.ApiServiceImpl
import app.com.access.R
import app.com.access.adapter.MainAdapter
import app.com.access.viewmodel.MainViewModal
import app.com.access.factory.MyViewModelFactory
import app.com.access.model.Item
import app.com.access.repository.MyRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var itemViewModel: MainViewModal
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        uiRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter()
        uiRecyclerView.adapter = adapter
        uiRecyclerView.addItemDecoration(MyItemDecorator())
        itemViewModel =  ViewModelProviders.of(this, MyViewModelFactory(MyRepository(ApiServiceImpl()))).get(MainViewModal::class.java)
        itemViewModel.fetchItems()
        itemViewModel.getItems().observe(this, Observer {
                adapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()

    }

    class MyItemDecorator : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.bottom = 20
            outRect.left = 20
            outRect.right = 20
        }
    }
}
