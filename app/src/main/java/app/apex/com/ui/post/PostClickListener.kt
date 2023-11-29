package app.apex.com.ui.post

import android.view.View

interface PostClickListener {
    fun onItemClicked(v: View, position: Int)
}