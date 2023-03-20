package com.example.fitpeopractical.view.fragment

/**
 * Created by Jeetesh Surana.
 */
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpeopractical.core.uI.BaseFragment
import com.example.fitpeopractical.data.remote.model.response.PhotosResponseItem
import com.example.fitpeopractical.databinding.FragmentDetailBinding
import com.example.fitpeopractical.util.Constant.KEY_PARCELABLE
import com.example.fitpeopractical.util.bindingAdapter.setPicture


class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private var userData: PhotosResponseItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable(KEY_PARCELABLE, PhotosResponseItem::class.java)
            } else {
                arguments?.getParcelable(KEY_PARCELABLE)
            }

            if (userData != null) {
                txtTitle.text = userData?.title
                userData?.url?.let { imgProfile.setPicture(it) }
            }

            imgBack.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }
}