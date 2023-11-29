package app.apex.com.ui.post

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.apex.com.R
import app.apex.com.data.Post
import app.apex.com.databinding.FragmentPostBinding
import app.apex.com.ui.comments.CommentActivity
import java.util.ArrayList

class PostFragment : Fragment(), PostClickListener {

    private var _binding: FragmentPostBinding? = null
    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    private var postArrayList = ArrayList<Post>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initPostAdapter()
        setObserver()

        postViewModel.callPostApi()

        return root
    }

    private fun initPostAdapter() {
        postAdapter = PostAdapter(activity as Context, this, postArrayList)
        binding.rvPost.adapter = postAdapter

        //binding.layout.rvFeed.setItemViewCacheSize(20);
        //binding.rvPost.setHasFixedSize(true)
    }

    private fun setObserver() {
        postViewModel.postData.observe(viewLifecycleOwner) {
            loadPostData(it)
        }
    }

    private fun loadPostData(postArrayList: ArrayList<Post>) {
        this.postArrayList.clear()
        this.postArrayList.addAll(postArrayList)
        postAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(v: View, position: Int) {
        when(v.id) {
            R.id.cv_post -> {
                activity?.let {
                    val bundle = Bundle()
                    bundle.putParcelable("POST_DATA", postArrayList[position])

                    val intent = Intent(it, CommentActivity::class.java)
                    intent.putExtras(bundle)
                    it.startActivity(intent)
                }
            }
        }
    }
}