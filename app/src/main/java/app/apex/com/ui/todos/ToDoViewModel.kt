package app.apex.com.ui.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coming soon..."
    }
    val text: LiveData<String> = _text
}