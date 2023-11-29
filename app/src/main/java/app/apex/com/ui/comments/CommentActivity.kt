package app.apex.com.ui.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import app.apex.com.data.Comment
import app.apex.com.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var commentAdapter: CommentAdapter

    private var commentArrayList = ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Action Bar title
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Comment"

        commentViewModel = ViewModelProvider(this)[CommentViewModel::class.java]

        initAdapter()
        setObserver()

        commentViewModel.callCommentApi()
    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter(this, commentArrayList)
        binding.rvComment.adapter = commentAdapter
    }

    private fun setObserver() {
        commentViewModel.commentData.observe(this) {
            loadCommentData(it)
        }
    }

    private fun loadCommentData(commentArrayList: ArrayList<Comment>) {
        this.commentArrayList.clear()
        this.commentArrayList.addAll(commentArrayList)
        commentAdapter.notifyDataSetChanged()
    }
}