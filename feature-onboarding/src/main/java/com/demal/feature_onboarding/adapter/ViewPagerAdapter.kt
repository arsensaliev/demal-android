package com.demal.feature_onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demal.feature_onboarding.fragments.WelcomeFragment

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    companion object{
        private const val POSITION_ONE = 1
        private const val POSITION_TWO = 2
        private const val POSITION_THREE = 3
    }

    private val fragments = arrayListOf<Fragment>(
        WelcomeFragment.newInstance(POSITION_ONE),
        WelcomeFragment.newInstance(POSITION_TWO),
        WelcomeFragment.newInstance(POSITION_THREE)
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}

