package app.com.access.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.com.access.api.ApiServiceImpl
import app.com.access.R
import app.com.access.viewmodel.DetailViewModel
import app.com.access.factory.MyViewModelFactory
import app.com.access.repository.MyRepository
import app.com.access.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        uiClose.setOnClickListener {
            finish()
        }
        detailViewModel =   ViewModelProviders.of(this, MyViewModelFactory(MyRepository(ApiServiceImpl()))).get(
            DetailViewModel::class.java)
        detailViewModel.fetchDetail(intent.extras?.getString("login") ?: "")
        detailViewModel.getDetail().observe(this, Observer {
            it.run {
                if (this != null) {
                    uiContent.visibility = View.VISIBLE
                    uiLoading.visibility = View.GONE
                    Picasso.get().load(avatarUrl).transform(CircleTransform()).into(uiAvatar);
                    uiName.text = name
                    uiBio.text = bio
                    uiLocation.text = location
                    uiLogin.text = login
                    uiBlog.text = blog
                    if (siteAdmin) {
                        uiSiteAdmin.visibility = View.VISIBLE
                    } else {
                        uiSiteAdmin.visibility = View.GONE
                    }
                }
            }

        })
    }
}