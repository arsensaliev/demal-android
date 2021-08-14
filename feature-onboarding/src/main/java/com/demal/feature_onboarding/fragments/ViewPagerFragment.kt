package com.demal.feature_onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.demal.feature_onboarding.R
import com.demal.feature_onboarding.adapter.ViewPagerAdapter
import me.relex.circleindicator.CircleIndicator3

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        val indicator3: CircleIndicator3 = view.findViewById(R.id.indicator)

        viewPager.adapter = adapter
        indicator3.setViewPager(viewPager)
    }


}