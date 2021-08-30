package com.demal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.demal.databinding.FragmentTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.user.User
import com.demal.view.core.view.BaseFragment
import me.relex.circleindicator.CircleIndicator3
import org.koin.android.viewmodel.ext.android.viewModel

class ViewPagerTourFragment :
    BaseFragment<FragmentTourBinding, LikableTour, ViewPagerTourViewModel>() {

    override var bindingNullable: FragmentTourBinding? = null

    override val viewModel: ViewPagerTourViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTourBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = listOf(
            R.drawable.tour_background_image,
            R.drawable.profile_bg_bitmap,
            R.drawable.profile_bg_dark
        )

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val adapter = ViewPagerTourAdapter(images)
        viewPager.adapter = adapter

        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)
    }

    override fun renderSuccess(data: LikableTour) {
        TODO("Not yet implemented")
    }
}