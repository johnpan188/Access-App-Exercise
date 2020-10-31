package app.com.access.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.com.access.R
import app.com.access.model.Item
import app.com.access.util.CircleTransform
import app.com.access.view.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*


class MainAdapter() : PagedListAdapter<Item ,MainAdapter.DataViewHolder>(DIFF_CALLBACK) {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item?) {
            if (item != null) {
                itemView.uiLogin.text = item.login
                if (item.siteAdmin) {
                    itemView.uiSiteAdmin.visibility = View.VISIBLE
                } else {
                    itemView.uiSiteAdmin.visibility = View.GONE
                }

                Picasso.get().load(item.avatarUrl).transform(CircleTransform()).into(itemView.uiAvatar)
                itemView.setOnClickListener {
                    val activity = itemView.context as Activity
                    val intent = Intent(activity, DetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("login", item.login)
                    intent.putExtras(bundle)
                    activity.startActivity(intent)
                }
            }
        }

    }
    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Item>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldConcert: Item,
                                         newConcert: Item) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: Item,
                                            newConcert: Item) = oldConcert == newConcert
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item, parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}