package com.example.serverconnection

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.serverconnection.network.MarsProperty
import com.example.serverconnection.overview.marsApiStatus


@BindingAdapter("ListData")
fun BindRecyclerView(recyclewview: RecyclerView, list: List<MarsProperty>?){
    val adapter=recyclewview.adapter as PhotoGridAdapter
    adapter.submitList(list)
}
@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl:String?){
    imgUrl?.let {
        val imgUri=it!!.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
@BindingAdapter("MarApiStatus")
fun bindstatus(statusimageview: ImageView, MarsApiStatus: marsApiStatus?){
    when(MarsApiStatus){
        marsApiStatus.LOADING->{
            statusimageview.visibility= View.VISIBLE
            statusimageview.setImageResource(R.drawable.loading_animation)
        }
        marsApiStatus.ERROR->{
            statusimageview.visibility= View.VISIBLE
            statusimageview.setImageResource(R.drawable.ic_connection_error)
        }
        marsApiStatus.DONE->{
            statusimageview.visibility= View.GONE
        }
    }
}
