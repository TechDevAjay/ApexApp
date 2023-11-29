package app.apex.com.ui.post


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.apex.com.R
import app.apex.com.data.Post
import app.apex.com.databinding.ItemPostBinding

class PostAdapter(
    context: Context,
    itemClickListener: PostClickListener,
    postArrayList: ArrayList<Post>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context
    private var itemClickListener: PostClickListener
    private var layoutInflater = LayoutInflater.from(context)
    private var postArrayList: ArrayList<Post>

    init {
        this.context = context
        this.itemClickListener = itemClickListener
        this.postArrayList = postArrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemPostBinding: ItemPostBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_post, parent, false)
        return ViewHolder(itemPostBinding)
    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.onBind(postArrayList[position], position)
    }

    inner class ViewHolder(itemPostBinding: ItemPostBinding): RecyclerView.ViewHolder(itemPostBinding.root) {
        private val itemPostBinding: ItemPostBinding
        init {
            this.itemPostBinding = itemPostBinding
        }

        fun onBind(post: Post, position: Int) {
            itemPostBinding.tvPostTitle.text = post.title
            itemPostBinding.tvPostBody.text = post.body

            itemPostBinding.cvPost.setOnClickListener{ view: View ->
                itemClickListener.onItemClicked(view, position)
            }
        }
    }
}