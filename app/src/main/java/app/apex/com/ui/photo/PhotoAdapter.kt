package app.apex.com.ui.photo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.apex.com.R
import app.apex.com.data.Photo
import app.apex.com.databinding.ItemPhotoBinding
import com.bumptech.glide.Glide


class PhotoAdapter(
    context: Context,
    photoArrayList: ArrayList<Photo>
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context
    private var layoutInflater = LayoutInflater.from(context)
    private var photoArrayList: ArrayList<Photo>
    init {
        this.context = context
        this.photoArrayList = photoArrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemPhotoBinding: ItemPhotoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_photo, parent, false)
        return ViewHolder(itemPhotoBinding)
    }

    override fun getItemCount(): Int {
        return photoArrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.onBind(photoArrayList[position])
    }

    inner class ViewHolder(itemPhotoBinding: ItemPhotoBinding): RecyclerView.ViewHolder(itemPhotoBinding.root) {
        private val  itemPhotoBinding: ItemPhotoBinding

        init {
            this.itemPhotoBinding = itemPhotoBinding
        }

        fun onBind(photo: Photo) {

            Glide
                .with(context)
                .asBitmap()
                .load(photo.url)
                .centerCrop()
                .sizeMultiplier(0.6f)
                .placeholder(R.drawable.ic_place_holder)
                .into(itemPhotoBinding.ivPhoto)
        }
    }
}