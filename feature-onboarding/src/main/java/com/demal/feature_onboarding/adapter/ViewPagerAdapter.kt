package com.demal.feature_onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demal.feature_onboarding.fragments.WelcomeFragment
import com.demal.feature_onboarding.fragments.WelcomeSecondFragment
import com.demal.feature_onboarding.fragments.WelcomeThirdFragment

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragments = arrayListOf<Fragment>(
        WelcomeFragment(),
        WelcomeSecondFragment(),
        WelcomeThirdFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}

