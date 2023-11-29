package app.apex.com.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.apex.com.data.Comment

class CommentViewModel: ViewModel() {

    private val TAG = CommentViewModel::class.java.simpleName

    private val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableCommentData: MutableLiveData<ArrayList<Comment>> = MutableLiveData()
    val commentData: LiveData<ArrayList<Comment>>
        get() = mutableCommentData

    private fun loadDemoData() {
        val list = ArrayList<Comment>()
        for (i in 1..10) {
            val comment = Comment(1,
                i,
                "Name $i",
                "$i@email.com",
                "expedita maiores dignissimos facilis\nipsum est rem est fugit velit sequi\neum odio dolores dolor totam\noccaecati ratione eius rem velit"
            )
            list.add(comment)
        }
        mutableCommentData.value = list
    }

    fun callCommentApi(postId: Int) {
        loadingState.value = true
        loadDemoData()
    }
}