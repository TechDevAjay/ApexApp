package app.apex.com.ui.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.apex.com.R
import app.apex.com.data.Comment
import app.apex.com.data.Post
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
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_up)

        commentViewModel = ViewModelProvider(this)[CommentViewModel::class.java]

        initAdapter()
        setObserver()
        initData()
    }

    private fun initData() {
        val postData: Post? = intent.getParcelableExtra("POST_DATA")
        supportActionBar?.title = postData?.title

        binding.tvPostTitle.text = postData?.title
        binding.tvPostBody.text = postData?.body

        //API Call
        if(postData?.id == null) {
            Toast.makeText(this, "Post data is empty, Error!", Toast.LENGTH_SHORT).show()
        } else {
            commentViewModel.callCommentApi(postData.id)
        }
    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter(this, commentArrayList)
        binding.rvComment.adapter = commentAdapter
    }

    private fun setObserver() {
        commentViewModel.commentData.observe(this) {loadCommentData(it) }
        commentViewModel.loadingState.observe(this) {loadStatus(it)}
    }

    private fun loadCommentData(commentArrayList: ArrayList<Comment>) {
        this.commentArrayList.clear()
        this.commentArrayList.addAll(commentArrayList)
        commentAdapter.notifyDataSetChanged()
    }

    private fun loadStatus(status: Boolean) {
        if(status) {
            binding.pbComment.visibility = View.VISIBLE
        } else {
            binding.pbComment.visibility = View.GONE
        }
    }
}