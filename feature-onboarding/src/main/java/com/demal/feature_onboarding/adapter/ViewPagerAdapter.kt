package com.demal.feature_onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.demal.feature_onboarding.databinding.FragmentWelcomeBinding
import com.demal.feature_onboarding.fragments.WelcomeFragment
import com.demal.feature_onboarding.utils.POSITION_ONE
import com.demal.feature_onboarding.utils.POSITION_THREE
import com.demal.feature_onboarding.utils.POSITION_TWO

class ViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,

    ) : FragmentStateAdapter(fm, lifecycle) {

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

