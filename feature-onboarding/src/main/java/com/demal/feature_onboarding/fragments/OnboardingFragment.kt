package com.demal.feature_onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.demal.feature_onboarding.OnboardingViewModel
import com.demal.feature_onboarding.R
import com.demal.feature_onboarding.adapter.ViewPagerAdapter
import me.relex.circleindicator.CircleIndicator3
import org.koin.android.viewmodel.ext.android.viewModel

class OnboardingFragment : Fragment() {

    private val viewModel: OnboardingViewModel by viewModel()

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
        val button_pager: Button = view.findViewById(R.id.button_welcome)

        viewPager.adapter = adapter
        indicator3.setViewPager(viewPager)

        button_pager.setOnClickListener {
            viewPager.currentItem += 1

            if (viewPager.currentItem == 3) {
                button_pager.text = resources.getString(R.string.welcomeButtonThird)
                viewModel.openHome()
            }
        }

    }
}