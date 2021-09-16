package com.demal.feature_onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.demal.feature_onboarding.R
import com.demal.feature_onboarding.databinding.FragmentWelcomeBinding
import com.demal.feature_onboarding.utils.POSITION
import com.demal.feature_onboarding.utils.POSITION_ONE
import com.demal.feature_onboarding.utils.POSITION_THREE
import com.demal.feature_onboarding.utils.POSITION_TWO

class WelcomeFragment() : Fragment() {

    private var bindingNullable: FragmentWelcomeBinding? = null
    private val binding get() = bindingNullable!!

    companion object {

        fun newInstance(fragmentState: Int) = WelcomeFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION, fragmentState)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentWelcomeBinding.inflate(inflater, container, false).apply {
        bindingNullable = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(POSITION) ?: POSITION_ONE

        init(position)
    }

    private fun init(statePosition: Int) {
        when (statePosition) {
            POSITION_ONE -> {
                initStateOne()
            }
            POSITION_TWO -> {
                initStateTwo()
            }
            POSITION_THREE -> {
                initStateThree()
            }
            else -> POSITION_ONE
        }
    }

    private fun initStateOne() {
        binding.tvTitle.text = resources.getString(R.string.welcomeTitle)
        binding.tvBody.text = resources.getString(R.string.welcomeBody)
        binding.layoutWelcome.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_welcome, null)
    }

    private fun initStateTwo() {
        binding.tvTitle.text = resources.getString(R.string.welcomeSecondTitle)
        binding.tvBody.text = resources.getString(R.string.welcomeSecondBody)

        binding.layoutWelcome.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_welcome_second, null)
    }

    private fun initStateThree() {
        binding.tvTitle.text = resources.getString(R.string.welcomeThirdTitle)
        binding.tvBody.text = resources.getString(R.string.welcomeThirdBody)
        binding.layoutWelcome.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_welcome_third, null)
    }

}