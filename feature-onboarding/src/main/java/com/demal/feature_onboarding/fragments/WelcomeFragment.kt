package com.demal.feature_onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.demal.feature_onboarding.R
import com.demal.feature_onboarding.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding = _binding!!

    companion object {

        private const val POSITION = "position"
        private const val POSITION_ONE = 1
        private const val POSITION_TWO = 2
        private const val POSITION_THREE = 3

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
        _binding = this
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
        binding.buttonWelcome.text = resources.getString(R.string.welcomeButton)
        binding.layoutWelcome.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_welcome, null)
    }

    private fun initStateTwo() {
        binding.tvTitle.text = resources.getString(R.string.welcomeSecondTitle)
        binding.tvBody.text = resources.getString(R.string.welcomeSecondBody)
        binding.buttonWelcome.text = resources.getString(R.string.welcomeButton)
        binding.layoutWelcome.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_welcome_second, null)
    }

    private fun initStateThree() {
        binding.tvTitle.text = resources.getString(R.string.welcomeThirdTitle)
        binding.tvBody.text = resources.getString(R.string.welcomeThirdBody)
        binding.buttonWelcome.text = resources.getString(R.string.welcomeButton)
        binding.layoutWelcome.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_welcome_third, null)
    }

}