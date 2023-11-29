package app.apex.com.ui.photo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import app.apex.com.data.local.Photo
import app.apex.com.databinding.FragmentPhotosBinding

class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoAdapter: PhotoAdapter

    private var photoArrayList = ArrayList<Photo>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)

        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initAdapter()
        setObserver()

        photoViewModel.callPhotoApi()

        return root
    }

    private fun initAdapter() {
        binding.rvPhoto.layoutManager = GridLayoutManager(activity, 3)

        photoAdapter = PhotoAdapter(activity as Context, photoArrayList)
        binding.rvPhoto.adapter = photoAdapter
    }

    private fun setObserver() {
        photoViewModel.photoData.observe(viewLifecycleOwner) {loadPhotoData(it) }
        photoViewModel.loadingState.observe(viewLifecycleOwner){loadStatus(it)}
    }

    private fun loadPhotoData(photoArrayList: ArrayList<Photo>) {
        this.photoArrayList.clear()
        this.photoArrayList.addAll(photoArrayList)
        photoAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadStatus(status: Boolean) {
        if(status) {
            binding.pbPhoto.visibility = View.VISIBLE
        } else {
            binding.pbPhoto.visibility = View.GONE
        }
    }
}