package app.apex.com.ui.comments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.apex.com.R
import app.apex.com.data.Comment
import app.apex.com.databinding.ItemPostCommentBinding

class CommentAdapter(
    context: Context,
    commentArrayList: ArrayList<Comment>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context
    private var layoutInflater = LayoutInflater.from(context)
    private var commentArrayList: ArrayList<Comment>

    init {
        this.context = context
        this.commentArrayList = commentArrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemCommentBinding: ItemPostCommentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_post_comment, parent, false)
        return ViewHolder(itemCommentBinding)
    }

    override fun getItemCount(): Int {
        return commentArrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.onBind(commentArrayList[position], position)
    }

    inner class ViewHolder(itemPostCommentBinding: ItemPostCommentBinding): RecyclerView.ViewHolder(itemPostCommentBinding.root) {
        private val itemPostCommentBinding: ItemPostCommentBinding

        init {
            this.itemPostCommentBinding = itemPostCommentBinding
        }

        fun onBind(comment: Comment, position: Int) {
            itemPostCommentBinding.tvName.text = comment.name
            itemPostCommentBinding.tvEmail.text = comment.email
            itemPostCommentBinding.tvCommentBody.text = comment.body

        }
    }
}