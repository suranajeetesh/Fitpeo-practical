package com.example.fitpeopractical.view.fragment

/**
 * Created by Jeetesh Surana.
 */
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fitpeopractical.R
import com.example.fitpeopractical.data.remote.model.response.PhotosResponseItem
import com.example.fitpeopractical.databinding.FragmentDashboardBinding
import com.example.fitpeopractical.util.Constant.KEY_PARCELABLE
import com.example.fitpeopractical.util.extensionFunction.addReplaceFragment
import com.example.fitpeopractical.view.adapter.PhotosAdapter
import com.example.fitpeopractical.viewmodel.DemoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private val homeViewModel: DemoViewModel by viewModels()

    private var photoLists = ArrayList<PhotosResponseItem>()
    private var mAdapter: PhotosAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        init()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        homeViewModel.photoLists.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                photoLists.clear()
                photoLists.addAll(it)
                mAdapter?.notifyDataSetChanged()
            }
        }
    }

    private fun init() {
        lifecycleScope.launch {
            homeViewModel.getData()
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvDashboard.layoutManager = layoutManager
        mAdapter = PhotosAdapter(photoLists, object : PhotosAdapter.ItemClickListener {
            override fun itemClick(position: Int) {
                val detailFragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(KEY_PARCELABLE, photoLists[position])
                    }
                }
                requireActivity().addReplaceFragment(R.id.fl_container,
                    detailFragment, addFragment = true, true)
            }
        })
        binding.rvDashboard.adapter = mAdapter
    }
}